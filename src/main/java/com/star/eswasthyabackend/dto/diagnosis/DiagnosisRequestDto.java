package com.star.eswasthyabackend.dto.diagnosis;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiagnosisRequestDto {

    private Integer id;

    private String description;

    private Integer patientDetailId;

    private Integer doctorDetailId;
}
