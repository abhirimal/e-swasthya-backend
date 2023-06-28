package com.star.eswasthyabackend.dto.diagnosis;

import com.star.eswasthyabackend.dto.prescription.PrescriptionResponseDto;
import com.star.eswasthyabackend.dto.testResult.TestResultRequestDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DiagnosisTestResultPrescriptionResponseDto {

    private DiagnosisResponseDto diagnosisResponseDto;

    private List<TestResultRequestDto> testResultRequestDtoList;

    private List<PrescriptionResponseDto> prescriptionResponseDtoList;
}
