package com.star.eswasthyabackend.service.user.patient;

import com.star.eswasthyabackend.dto.user.patient.PatientDetailsRequestDto;
import com.star.eswasthyabackend.exception.AppException;
import com.star.eswasthyabackend.model.user.User;
import com.star.eswasthyabackend.model.user.patient.PatientDetails;
import com.star.eswasthyabackend.repository.user.UserRepository;
import com.star.eswasthyabackend.repository.user.patient.PatientDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PatientDetailsServiceImpl implements PatientDetailsService {

    private final PatientDetailsRepository patientDetailsRepository;
    private final UserRepository userRepository;
    @Override
    public Integer savePatientDetails(PatientDetailsRequestDto requestDto) {

        PatientDetails patientDetails;

        if(requestDto.getId()!=null){
            patientDetails = patientDetailsRepository.findById(requestDto.getId())
                    .orElseThrow(()-> new AppException("Patient not found for given id.", HttpStatus.BAD_REQUEST));
        }
        else{
            patientDetails = new PatientDetails();
        }

        User existingUser = userRepository.findById(requestDto.getUserId())
                .orElseThrow(()-> new AppException("Invalid user id.", HttpStatus.BAD_REQUEST));

        patientDetails.setFirstName(existingUser.getFirstName());
        patientDetails.setLastName(existingUser.getLastName());
        patientDetails.setEmail(existingUser.getEmail());

        patientDetails.setPhoneNumber(requestDto.getPhoneNumber());
        patientDetails.setCitizenshipNo(requestDto.getCitizenshipNo());
        //logic to generate unique health id
        patientDetails.setMedicalRecordId(null);
        patientDetails.setBloodGroup(requestDto.getBloodGroup());
        patientDetails.setWeight(requestDto.getWeight());
        patientDetails.setDateOfBirth(requestDto.getDateOfBirth());
        patientDetails.setUser(existingUser);
        //location
        patientDetails.setProvince(requestDto.getProvince());
        patientDetails.setDistrict(requestDto.getDistrict());
        patientDetails.setWard(requestDto.getWard());
        patientDetails.setStreet(requestDto.getStreet());

        patientDetailsRepository.saveAndFlush(patientDetails);
        return patientDetails.getId();
    }

    @Override
    public Map<String, String> getPatientDetails(Integer id) {
        return patientDetailsRepository.findPatientDetail(id);
    }

    @Override
    public List<Map<String, String>> getAll() {
        return patientDetailsRepository.findAllPatientDetail();
    }


}
