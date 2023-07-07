package com.star.eswasthyabackend.dto.diagnosis;

import com.star.eswasthyabackend.dto.prescription.PrescriptionRequestDto;
import com.star.eswasthyabackend.dto.testResult.TestResultRequestDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class DiagnosisTestResultPrescriptionRequestDto {

    @Valid
    private DiagnosisRequestDto diagnosis;

    @Valid
    private List<TestResultRequestDto> testResultList;

    @Valid
    private List<PrescriptionRequestDto> prescriptionList;

}
