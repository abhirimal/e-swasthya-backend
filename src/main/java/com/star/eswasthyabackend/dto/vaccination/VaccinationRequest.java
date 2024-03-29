package com.star.eswasthyabackend.dto.vaccination;

import com.star.eswasthyabackend.repository.vaccination.VaccinationRepository;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class VaccinationRequest {

    private Integer id;

    @NotBlank(message = "Vaccination name cannot be empty")
    private String vaccineName;

    private LocalDate vaccinationDate;

    @NotNull(message = "Dosage cannot be empty")
    private Integer dosage;

    @NotNull(message = "Patient Id cannot be empty")
    private Integer patientId;

    public VaccinationRequest(Integer id, String vaccineName, String vaccinationDate, Integer dosage, Integer patientId){
        this.id = id;
        this.vaccineName = vaccineName;
        this.vaccinationDate = LocalDate.parse(vaccinationDate);
        this.dosage = dosage;
        this.patientId = patientId;
    }
}
