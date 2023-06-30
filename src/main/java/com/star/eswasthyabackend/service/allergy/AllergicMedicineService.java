package com.star.eswasthyabackend.service.allergy;

import com.star.eswasthyabackend.dto.allergy.AllergicMedicineRequestDto;

public interface AllergicMedicineService {
    Integer saveAllergyList(AllergicMedicineRequestDto allergicMedicineRequestDto);
}
