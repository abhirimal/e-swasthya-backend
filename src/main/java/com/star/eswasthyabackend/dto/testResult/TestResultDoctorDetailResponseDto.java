package com.star.eswasthyabackend.dto.testResult;

import com.star.eswasthyabackend.model.doctor.DoctorDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestResultDoctorDetailResponseDto {

    private Integer doctorDetailId;

    private String firstName;

    private String lastName;

    private String email;

    private String specialization;

    public TestResultDoctorDetailResponseDto(DoctorDetails recommendedDoctorDetail) {
        doctorDetailId = recommendedDoctorDetail.getDoctorDetailId();
        firstName = recommendedDoctorDetail.getFirstName();
        lastName = recommendedDoctorDetail.getLastName();
        email = recommendedDoctorDetail.getEmail();
        specialization = recommendedDoctorDetail.getSpecialization();
    }
}
