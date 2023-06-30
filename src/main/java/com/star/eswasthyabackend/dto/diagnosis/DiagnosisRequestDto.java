package com.star.eswasthyabackend.dto.diagnosis;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiagnosisRequestDto {

    private Integer id;

    private Integer appointmentId;

    private String diseaseName;

    private String diseaseType;

    private String diagnosisDescription;

    private Integer patientDetailId;

    private Integer doctorDetailId;
}
