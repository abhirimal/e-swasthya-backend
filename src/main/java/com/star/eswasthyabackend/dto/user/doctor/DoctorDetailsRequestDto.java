package com.star.eswasthyabackend.dto.user.doctor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDetailsRequestDto {

    private Integer doctorDetailId;

    private Integer userId;

    private String nmcLicenseNo;

    private String phoneNumber;

    private String education;

    private String specialization;

    private String experience;

    private String gender;

    private String associatedHospital;

    private String location;
}
