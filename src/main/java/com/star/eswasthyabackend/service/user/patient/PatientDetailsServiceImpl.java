package com.star.eswasthyabackend.service.user.patient;

import com.star.eswasthyabackend.dto.user.patient.PatientDetailsRequestDto;
import com.star.eswasthyabackend.dto.user.patient.UpdateHeightWeightRequestPojo;
import com.star.eswasthyabackend.exception.AppException;
import com.star.eswasthyabackend.model.User;
import com.star.eswasthyabackend.model.location.District;
import com.star.eswasthyabackend.model.location.Location;
import com.star.eswasthyabackend.model.location.Municipality;
import com.star.eswasthyabackend.model.patient.PatientDetails;
import com.star.eswasthyabackend.repository.AppointmentRepository;
import com.star.eswasthyabackend.repository.location.DistrictRepository;
import com.star.eswasthyabackend.repository.location.LocationRepository;
import com.star.eswasthyabackend.repository.location.MunicipalityRepository;
import com.star.eswasthyabackend.repository.user.UserRepository;
import com.star.eswasthyabackend.repository.user.patient.PatientDetailsRepository;
import com.star.eswasthyabackend.utility.AuthenticationUtil;
import com.star.eswasthyabackend.utility.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PatientDetailsServiceImpl implements PatientDetailsService {

    private final PatientDetailsRepository patientDetailsRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
    private final DistrictRepository districtRepository;
    private final MunicipalityRepository municipalityRepository;
    private final JWTUtil jwtUtil;
    private final AuthenticationUtil authenticationUtil;
    private final AppointmentRepository appointmentRepository;
    @Override
    public String savePatientDetails(PatientDetailsRequestDto requestDto) {

        User existingUser = userRepository.findById(requestDto.getUserId())
                .orElseThrow(()-> new AppException("User not found for given user id.", HttpStatus.BAD_REQUEST));

        if(Boolean.FALSE.equals(existingUser.getIsVerified())){
            throw new AppException("User account is not verified yet.", HttpStatus.BAD_REQUEST);
        }

        PatientDetails patientDetails;

        if(requestDto.getPatientDetailId()!=null){
            patientDetails = patientDetailsRepository.findById(requestDto.getPatientDetailId())
                    .orElseThrow(()-> new AppException("Patient not found for given id.", HttpStatus.BAD_REQUEST));
        }
        else{
            //check if patient details is already filled
            Integer count = patientDetailsRepository.checkIfDataExists(requestDto.getUserId());
            if(count >=1){
                throw new AppException("Patient data already saved.",HttpStatus.BAD_REQUEST );
            }
            patientDetails = new PatientDetails();
        }

        patientDetails.setFirstName(existingUser.getFirstName());
        patientDetails.setLastName(existingUser.getLastName());
        patientDetails.setEmail(existingUser.getEmail());

        Integer phoneNumberCount = patientDetailsRepository.checkPhoneNumberCount(requestDto.getPhoneNumber(), requestDto.getUserId());
        if(phoneNumberCount > 0){
            throw new AppException("Phone number is already associated with another user.", HttpStatus.BAD_REQUEST);
        }
        patientDetails.setPhoneNumber(requestDto.getPhoneNumber());

        Integer citizenshipCount = patientDetailsRepository.checkCitizenshipCount(requestDto.getCitizenshipNo(), requestDto.getUserId());
        if(citizenshipCount > 0){
            throw new AppException("User with provided citizenship number already exists.", HttpStatus.BAD_REQUEST);
        }
        patientDetails.setCitizenshipNo(requestDto.getCitizenshipNo());
        patientDetails.setBloodGroup(requestDto.getBloodGroup());
        patientDetails.setWeight(requestDto.getWeight());
        patientDetails.setHeight(requestDto.getHeight());
        patientDetails.setGender(requestDto.getGender());
        patientDetails.setDateOfBirth(requestDto.getDateOfBirth());
        patientDetails.setImagePath(requestDto.getImagePath());

        LocalDate dateOfBirth  = requestDto.getDateOfBirth();
        Integer age = Math.toIntExact(ChronoUnit.YEARS.between(dateOfBirth, LocalDate.now()));
        patientDetails.setAge(age);
        patientDetails.setUser(existingUser);

        //location
        Location location = new Location();
        Integer districtId = districtRepository.findDistrictIdByMunicipalityId(requestDto.getMunicipalityId());
        District district = districtRepository.findById(districtId)
                        .orElseThrow(()-> new AppException("District not found for given district id", HttpStatus.BAD_REQUEST));
        Municipality municipality = municipalityRepository.findById(requestDto.getMunicipalityId())
                .orElseThrow(()-> new AppException("Municipality not found for given municipality id.", HttpStatus.BAD_REQUEST));
        location.setDistrict(district);
        location.setMunicipality(municipality);
        location.setStreetAddress(requestDto.getStreetAddress());
        location = locationRepository.saveAndFlush(location);
        patientDetails.setLocation(location);
        patientDetailsRepository.saveAndFlush(patientDetails);

        String medicalRecordNumber = generateUniqueMedicalRecordNumber(patientDetails.getPatientDetailId());
        patientDetails.setMedicalRecordNumber(medicalRecordNumber);
        patientDetailsRepository.saveAndFlush(patientDetails);

        existingUser.setIsFormFilled(true);
        userRepository.save(existingUser);

        if(SecurityContextHolder.getContext().getAuthentication()==null){
            return null;
        }
        else {
            return jwtUtil.generateNewToken();
        }
    }

    @Override
    public Map<String, String>  getPatientDetails(Integer id) {
        Integer patientId = authenticationUtil.getPatientId();
        Integer doctorId = authenticationUtil.getDoctorId();

        //if patient is logged in
        if(patientId != null){
            if(!patientId.equals(id)){
                throw new AppException("You are not authorized to access this resource", HttpStatus.UNAUTHORIZED);
            }
        }
        else {
            Integer countRelation = appointmentRepository.countRelationOfDoctorAndPatient(id, doctorId);
            if(countRelation == 0){
                throw new AppException("You are not authorized to access this resource", HttpStatus.UNAUTHORIZED);
            }
        }
        return patientDetailsRepository.findPatientDetail(id);
    }

    @Override
    public List<Map<String, String>> getAll() {
        return patientDetailsRepository.listAllPatient();
    }

    @Override
    public Map<String, Object> getPatientDetailsByUserId(Integer id) {
        Integer loggedInUserId = authenticationUtil.getUserId();
        if (!Objects.equals(loggedInUserId, id)){
            throw new AppException("You are not authorized to access this resource", HttpStatus.UNAUTHORIZED);
        }
        return patientDetailsRepository.getPatientDetailByUserId(id);
    }

    @Override
    public Boolean updateHeightAndWeight(UpdateHeightWeightRequestPojo requestPojo) {
        PatientDetails patientDetails = patientDetailsRepository.findById(requestPojo.getPatientId())
                .orElseThrow(()-> new AppException("Patient not found for given id.", HttpStatus.BAD_REQUEST));
        patientDetails.setWeight(requestPojo.getWeight());
        patientDetails.setHeight(requestPojo.getHeight());
        patientDetailsRepository.save(patientDetails);
        return true;
    }

    public String generateUniqueMedicalRecordNumber(Integer id){
        String formattedId = String.format("%04d", id);
        return "MRN-"+formattedId;
    }
}
