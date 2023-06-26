package com.star.eswasthyabackend.dto.diagnosis;

import com.star.eswasthyabackend.dto.medication.PrescriptionRequestDto;
import com.star.eswasthyabackend.dto.testResult.TestResultRequestDto;
import com.star.eswasthyabackend.model.Prescription;
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
