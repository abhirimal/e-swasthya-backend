package com.star.eswasthyabackend.dto.prescription;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class PrescriptionRequestDto {

    private Integer id;

    @NotBlank(message = "Medicine name cannot be empty")
    private String medicineName;

    @NotBlank(message = "Medicine type cannot be empty")
    private String medicineType;

    private Double dosageInUnit;

    @NotBlank(message = "Frequency cannot be empty")
    private String frequencyPerDay;

    @NotBlank(message = "Additional note cannot be empty")
    private String additionalNote;

    private LocalDate startDate;

    @NotNull(message = "Duration cannot be empty")
    private Integer durationInDays;

    @NotNull(message = "patient id cannot be empty")
    private Integer patientDetailId;

    @NotNull(message = "Doctor Id cannot be empty")
    private Integer doctorDetailId;
}
