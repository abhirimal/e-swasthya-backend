package com.star.eswasthyabackend.dto.allergy;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AllergicMedicineRequestDto {

    private Integer patientDetailId;
    private List<AllergicMedicineName> allergicMedicineList;

    @Getter
    @Setter
    public static class AllergicMedicineName {

        private Integer id;

        private String allergicMedicineName;
    }
}