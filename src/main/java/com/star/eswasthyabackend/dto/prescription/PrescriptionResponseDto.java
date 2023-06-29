package com.star.eswasthyabackend.dto.prescription;

import com.star.eswasthyabackend.model.Prescription;
import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.asm.Advice;

import java.time.LocalDate;

@Getter
@Setter
public class PrescriptionResponseDto {

    private Integer id;

    private String medicineName;

    private Double dosageInUnit;

    private String frequencyPerDay;

    private String additionalNote;

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer durationInDays;

    private Boolean isActive;

    public PrescriptionResponseDto(Prescription newPrescription) {
        id = newPrescription.getId();
        medicineName = newPrescription.getMedicineName();
        dosageInUnit = newPrescription.getDosageInUnit();
        frequencyPerDay = newPrescription.getFrequencyPerDay();
        additionalNote = newPrescription.getAdditionalNote();
        startDate = newPrescription.getStartDate();
        endDate = newPrescription.getEndDate();
        durationInDays = newPrescription.getDurationInDays();
        isActive = newPrescription.getIsActive();
    }
}
