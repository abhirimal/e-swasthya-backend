package com.star.eswasthyabackend.dto.diagnosis;

import com.star.eswasthyabackend.dto.testResult.TestResultDoctorDetailResponseDto;
import com.star.eswasthyabackend.dto.testResult.TestResultPatientDetailResponseDto;
import com.star.eswasthyabackend.dto.user.doctor.DoctorDetailResponseDto;
import com.star.eswasthyabackend.model.TestResult;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DiagnosisResponseDto {

    private Integer id;

    private String description;

    private List<TestResult> testResultList;

    private TestResultDoctorDetailResponseDto doctorDetail;

    private TestResultPatientDetailResponseDto patientDetail;
}
