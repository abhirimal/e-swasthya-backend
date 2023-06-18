package com.star.eswasthyabackend.dto.testResult;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestResultRequestDto {

    private Integer id;

    private String testName;

    private String result;

    private String description;

    private LocalDate testDate;

    private Integer patientDetailId;

    private Integer recommendedDoctorDetailId;

    private String imagePath;
}
