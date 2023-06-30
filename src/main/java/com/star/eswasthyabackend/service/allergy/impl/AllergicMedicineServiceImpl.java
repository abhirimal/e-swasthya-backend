package com.star.eswasthyabackend.service.allergy.impl;

import com.star.eswasthyabackend.dto.allergy.AllergicMedicineRequestDto;
import com.star.eswasthyabackend.exception.AppException;
import com.star.eswasthyabackend.model.AllergicMedicine;
import com.star.eswasthyabackend.model.patient.PatientDetails;
import com.star.eswasthyabackend.repository.AllergicMedicineRepository;
import com.star.eswasthyabackend.repository.user.patient.PatientDetailsRepository;
import com.star.eswasthyabackend.service.allergy.AllergicMedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AllergicMedicineServiceImpl implements AllergicMedicineService {

    private final AllergicMedicineRepository allergicMedicineRepository;
    private final PatientDetailsRepository patientDetailsRepository;
    @Override
    public Integer saveAllergyList(AllergicMedicineRequestDto allergicMedicineRequestDto) {

        PatientDetails patientDetails = patientDetailsRepository.findById(allergicMedicineRequestDto.getPatientDetailId())
                .orElseThrow(()-> new AppException("Patient not found for given id.", HttpStatus.BAD_REQUEST));
        List<AllergicMedicine> allergicMedicineList = new ArrayList<>();
        allergicMedicineRequestDto.getAllergicMedicineList().forEach(
                allergyRequest -> {
                    AllergicMedicine allergicMedicine;
                    if(allergyRequest!=null){
                        allergicMedicine = allergicMedicineRepository.findById(allergyRequest.getId())
                                .orElseThrow(()-> new AppException("Allergic Medicine not found for given id.", HttpStatus.BAD_REQUEST));
                    }
                    else{
                        allergicMedicine = new AllergicMedicine();
                    }
                    allergicMedicine.setAllergicMedicineName(allergyRequest.getAllergicMedicineName());
                    allergicMedicine.setPatientDetails(patientDetails);
                    allergicMedicineList.add(allergicMedicine);
                }
        );
        allergicMedicineRepository.saveAll(allergicMedicineList);
        return null;
    }
}
