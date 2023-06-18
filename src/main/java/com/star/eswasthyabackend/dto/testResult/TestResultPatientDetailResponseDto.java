package com.star.eswasthyabackend.dto.testResult;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestResultPatientDetailResponseDto {

    private Integer patientDetailId;

    private String medicalRecordNumber;
    private String firstName;

    private String lastName;
}
