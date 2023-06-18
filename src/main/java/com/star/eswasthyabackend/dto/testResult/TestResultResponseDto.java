package com.star.eswasthyabackend.dto.testResult;

import com.star.eswasthyabackend.dto.user.doctor.DoctorDetailResponseDto;
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

    private String result;

    private String description;

    private LocalDate testDate;

    private Integer patientDetailId;

    private String imagePath;

    private DoctorDetailResponseDto recommendedDoctorDetail;
}
