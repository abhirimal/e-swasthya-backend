package com.star.eswasthyabackend.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class VaccinationRequest {

    private Integer id;

    private String vaccineName;

    private LocalDate vaccinationDate;

    private Integer dosage;

    @NotNull
    private Integer patientId;
}
