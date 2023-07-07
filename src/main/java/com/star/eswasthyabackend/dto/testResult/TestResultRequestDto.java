package com.star.eswasthyabackend.dto.testResult;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestResultRequestDto {

    private Integer id;

    @NotBlank(message = "Test name cannot be empty")
    private String testName;

    @NotBlank(message = "Test type cannot be empty")
    private String testType;

    @NotBlank(message = "Test result cannot be empty")
    private String result;

    @NotBlank(message = "Test description cannot be empty")
    private String description;

    private LocalDate testDate;

    @NotNull(message = "Patient Id cannot be empty")
    private Integer patientDetailId;

    @NotNull(message = "Doctor Id cannot be empty")
    private Integer recommendedDoctorDetailId;

    private String imagePath;

    public TestResultRequestDto(Integer id, String testName, String testType, String result, String description,
                                String testDate, Integer patientId, Integer doctorId){
        this.id = id;
        this.testName = testName;
        this.testType = testType;
        this.result = result;
        this.description = description;
        this.testDate = LocalDate.parse(testDate);
        this.patientDetailId = patientId;
        this.recommendedDoctorDetailId = doctorId;
    }
}
