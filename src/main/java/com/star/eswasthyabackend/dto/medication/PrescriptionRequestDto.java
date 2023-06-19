package com.star.eswasthyabackend.dto.medication;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PrescriptionRequestDto {

    private Integer id;

    private String medicineName;

    private Double dosageInUnit;

    private String frequency;

    private String additionalNote;

    private LocalDate prescribedDate;

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer durationInDays;

    private Integer patientDetailId;

    private Integer doctorDetailId;
}
