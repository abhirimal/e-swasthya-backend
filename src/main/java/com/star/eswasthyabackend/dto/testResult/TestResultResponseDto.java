package com.star.eswasthyabackend.dto.testResult;

import com.star.eswasthyabackend.model.TestResult;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class TestResultResponseDto {

    private Integer id;

    private String testName;

    private String testType;

    private String result;

    private String description;

    private LocalDate testDate;

    private String imagePath;

    private TestResultDoctorDetailResponseDto doctorDetail;

    private TestResultPatientDetailResponseDto patientDetail;

    public TestResultResponseDto(TestResult testResult) {
        this.id = testResult.getId();
        this.testName = testResult.getTestName();
        this.testType = testResult.getTestType();
        this.result = testResult.getResult();
        this.description = testResult.getDescription();
        this.testDate = testResult.getTestDate();
        this.imagePath = testResult.getImagePath();
        this.doctorDetail = new TestResultDoctorDetailResponseDto(testResult.getRecommendedDoctorDetail());
        this.patientDetail = new TestResultPatientDetailResponseDto(testResult.getPatientDetail());
    }

    public TestResultResponseDto(TestResult testResult, Integer id) {
        this.id = id;
        this.testName = testResult.getTestName();
        this.testType = testResult.getTestType();
        this.result = testResult.getResult();
        this.description = testResult.getDescription();
        this.testDate = testResult.getTestDate();
        this.imagePath = testResult.getImagePath();
    }
}