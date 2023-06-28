package com.star.eswasthyabackend.dto.diagnosis;

import com.star.eswasthyabackend.dto.prescription.PrescriptionRequestDto;
import com.star.eswasthyabackend.dto.testResult.TestResultRequestDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DiagnosisTestResultPrescriptionRequestDto {

    private DiagnosisRequestDto diagnosis;

    private List<TestResultRequestDto> testResultList;

    private List<PrescriptionRequestDto> prescriptionList;

}
