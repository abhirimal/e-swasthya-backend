package com.star.eswasthyabackend.dto.allergy;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class AllergicMedicineRequestDto {

    @NotNull(message = "Patient Id cannot be empty")
    private Integer patientDetailId;
    private List<AllergicMedicineName> allergicMedicineList;

    @Getter
    @Setter
    public static class AllergicMedicineName {

        private Integer id;

        @NotBlank(message = "Allergic Medicine cannot be empty.")
        private String allergicMedicineName;
    }
}