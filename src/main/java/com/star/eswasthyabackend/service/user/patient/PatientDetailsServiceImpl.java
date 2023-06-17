package com.star.eswasthyabackend.service.user.patient;

import com.star.eswasthyabackend.dto.user.patient.PatientDetailsRequestDto;
import com.star.eswasthyabackend.exception.AppException;
import com.star.eswasthyabackend.model.User;
import com.star.eswasthyabackend.model.location.District;
import com.star.eswasthyabackend.model.location.Location;
import com.star.eswasthyabackend.model.location.Municipality;
import com.star.eswasthyabackend.model.patient.PatientDetails;
import com.star.eswasthyabackend.repository.location.DistrictRepository;
import com.star.eswasthyabackend.repository.location.LocationRepository;
import com.star.eswasthyabackend.repository.location.MunicipalityRepository;
import com.star.eswasthyabackend.repository.user.UserRepository;
import com.star.eswasthyabackend.repository.user.patient.PatientDetailsRepository;
import com.star.eswasthyabackend.utility.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PatientDetailsServiceImpl implements PatientDetailsService {

    private final PatientDetailsRepository patientDetailsRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
    private final DistrictRepository districtRepository;
    private final MunicipalityRepository municipalityRepository;
    private final JWTUtil jwtUtil;
    @Override
    public String savePatientDetails(PatientDetailsRequestDto requestDto) {


        User existingUser = userRepository.findById(requestDto.getUserId())
                .orElseThrow(()-> new AppException("User not found for given user id.", HttpStatus.BAD_REQUEST));

        if(Boolean.FALSE.equals(existingUser.getIsVerified())){
            throw new AppException("User account is not verified yet.", HttpStatus.BAD_REQUEST);
        }

        //check if patient details is already filled
        Integer count = patientDetailsRepository.checkIfDataExists(requestDto.getUserId());
        if(count >=1){
            throw new AppException("Patient data already saved.",HttpStatus.BAD_REQUEST );
        }

        PatientDetails patientDetails;

        if(requestDto.getPatientDetailId()!=null){
            patientDetails = patientDetailsRepository.findById(requestDto.getPatientDetailId())
                    .orElseThrow(()-> new AppException("Patient not found for given id.", HttpStatus.BAD_REQUEST));
        }
        else{
            patientDetails = new PatientDetails();
        }

        patientDetails.setFirstName(existingUser.getFirstName());
        patientDetails.setLastName(existingUser.getLastName());
        patientDetails.setEmail(existingUser.getEmail());
        patientDetails.setPhoneNumber(requestDto.getPhoneNumber());
        patientDetails.setCitizenshipNo(requestDto.getCitizenshipNo());
        patientDetails.setBloodGroup(requestDto.getBloodGroup());
        patientDetails.setWeight(requestDto.getWeight());
        patientDetails.setHeight(requestDto.getHeight());
        patientDetails.setGender(requestDto.getGender());
        patientDetails.setDateOfBirth(requestDto.getDateOfBirth());

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

        return jwtUtil.generateNewToken();
    }

    @Override
    public Map<String, String> getPatientDetails(Integer id) {
        return patientDetailsRepository.findPatientDetail(id);
    }

    @Override
    public List<Map<String, String>> getAll() {
        return patientDetailsRepository.listAllPatient();
    }

    public String generateUniqueMedicalRecordNumber(Integer id){
        String formattedId = String.format("%04d", id);
        return "MRN-"+formattedId;
    }

    public void saveCustomData(PatientDetailsRequestDto requestDto){

        User existingUser = userRepository.findById(requestDto.getUserId())
                .orElseThrow(()-> new AppException("User not found for given user id.", HttpStatus.BAD_REQUEST));

        if(Boolean.FALSE.equals(existingUser.getIsVerified())){
            throw new AppException("User account is not verified yet.", HttpStatus.BAD_REQUEST);
        }

        //check if patient details is already filled
        Integer count = patientDetailsRepository.checkIfDataExists(requestDto.getUserId());
        if(count >=1){
            throw new AppException("Patient data already saved.",HttpStatus.BAD_REQUEST );
        }

        PatientDetails patientDetails;

        if(requestDto.getPatientDetailId()!=null){
            patientDetails = patientDetailsRepository.findById(requestDto.getPatientDetailId())
                    .orElseThrow(()-> new AppException("Patient not found for given id.", HttpStatus.BAD_REQUEST));
        }
        else{
            patientDetails = new PatientDetails();
        }

        patientDetails.setFirstName(existingUser.getFirstName());
        patientDetails.setLastName(existingUser.getLastName());
        patientDetails.setEmail(existingUser.getEmail());
        patientDetails.setPhoneNumber(requestDto.getPhoneNumber());
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
    }
}
