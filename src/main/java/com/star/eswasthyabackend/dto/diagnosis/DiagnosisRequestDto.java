package com.star.eswasthyabackend.dto.diagnosis;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DiagnosisRequestDto {

    private Integer id;

    @NotNull(message = "Appointment Id cannot be empty")
    private Integer appointmentId;

    @NotBlank(message = "Disease name cannot be empty")
    private String diseaseName;

    @NotBlank(message = "Disease type cannot be empty")
    private String diseaseType;

    @NotBlank(message = "Diagnosis description cannot be empty")
    private String diagnosisDescription;

    @NotNull(message = "Patient Id cannot be empty")
    private Integer patientDetailId;

    @NotNull(message = "Doctor Id cannot be empty")
    private Integer doctorDetailId;

    public DiagnosisRequestDto(Integer id, Integer appointmentId, String diseaseName, String diseaseType, String diagnosisDescription, Integer patientId, Integer doctorId){
        this.id=id;
        this.appointmentId = appointmentId;
        this.diseaseName = diseaseName;
        this.diseaseType = diseaseType;
        this.diagnosisDescription = diagnosisDescription;
        this.patientDetailId = patientId;
        this.doctorDetailId = doctorId;
    }
}
