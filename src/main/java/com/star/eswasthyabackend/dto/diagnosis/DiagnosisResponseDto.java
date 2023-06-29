package com.star.eswasthyabackend.dto.diagnosis;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.star.eswasthyabackend.dto.prescription.PrescriptionResponseDto;
import com.star.eswasthyabackend.dto.testResult.TestResultDoctorDetailResponseDto;
import com.star.eswasthyabackend.dto.testResult.TestResultPatientDetailResponseDto;
import com.star.eswasthyabackend.dto.testResult.TestResultResponseDto;
import com.star.eswasthyabackend.model.Prescription;
import com.star.eswasthyabackend.model.TestResult;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class DiagnosisResponseDto {

    private Integer id;

    private String description;

    private String disease;

    private LocalDate date;

    private String hospitalName;

    private List<TestResultResponseDto> testResultList;

    private List<PrescriptionResponseDto> prescriptionList;

    private TestResultDoctorDetailResponseDto doctorDetail;

    private TestResultPatientDetailResponseDto patientDetail;

}
