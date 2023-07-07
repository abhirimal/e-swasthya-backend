package com.star.eswasthyabackend.service.allergy.impl;

import com.star.eswasthyabackend.dto.allergy.AllergicMedicineRequestDto;
import com.star.eswasthyabackend.exception.AppException;
import com.star.eswasthyabackend.model.AllergicMedicine;
import com.star.eswasthyabackend.model.patient.PatientDetails;
import com.star.eswasthyabackend.repository.AllergicMedicineRepository;
import com.star.eswasthyabackend.repository.user.patient.PatientDetailsRepository;
import com.star.eswasthyabackend.service.allergy.AllergicMedicineService;
import com.star.eswasthyabackend.utility.AuthenticationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AllergicMedicineServiceImpl implements AllergicMedicineService {

    private final AllergicMedicineRepository allergicMedicineRepository;
    private final PatientDetailsRepository patientDetailsRepository;
    private final AuthenticationUtil authenticationUtil;

    @Override
    public Integer saveAllergyList(AllergicMedicineRequestDto allergicMedicineRequestDto) {

        PatientDetails patientDetails = patientDetailsRepository.findById(allergicMedicineRequestDto.getPatientDetailId())
                .orElseThrow(() -> new AppException("Patient not found for given id.", HttpStatus.BAD_REQUEST));
        List<AllergicMedicine> allergicMedicineList = new ArrayList<>();
        allergicMedicineRequestDto.getAllergicMedicineList().forEach(
                allergyRequest -> {
                    AllergicMedicine allergicMedicine = allergicMedicineRepository
                            .findAllergicMedicineByNameAndPatientId(allergyRequest.getAllergicMedicineName(), allergicMedicineRequestDto.getPatientDetailId());
                    if (allergicMedicine != null){
                        allergicMedicineList.add(allergicMedicine);
                    }
                    else {
                        allergicMedicine = new AllergicMedicine();
                        allergicMedicine.setAllergicMedicineName(allergyRequest.getAllergicMedicineName());
                        allergicMedicine.setPatientDetails(patientDetails);
                        allergicMedicineList.add(allergicMedicine);
                    }
                }
        );
        allergicMedicineRepository.saveAll(allergicMedicineList);
        return null;
    }

    @Override
    public List<Map<String, String>> listAllergicMedicineByPatientId(Integer patientId) {
        return allergicMedicineRepository.listAllergicMedicineByPatientId(patientId);
    }

    @Override
    public Boolean deleteById(Integer allergicMedicineId) {
        String role = authenticationUtil.getRole();
        if (!Objects.equals(role, "DOCTOR")) {
            throw new AppException("You are not authorized to access this resource", HttpStatus.BAD_REQUEST);
        }
        allergicMedicineRepository.deleteById(allergicMedicineId);
        return true;
    }
}