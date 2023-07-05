package com.star.eswasthyabackend.service.allergy;

import com.star.eswasthyabackend.dto.allergy.AllergicMedicineRequestDto;

import java.util.List;
import java.util.Map;

public interface AllergicMedicineService {
    Integer saveAllergyList(AllergicMedicineRequestDto allergicMedicineRequestDto);

    List<Map<String, String>> listAllergicMedicineByPatientId(Integer patientId);

    Boolean deleteById(Integer allergyId);
}
