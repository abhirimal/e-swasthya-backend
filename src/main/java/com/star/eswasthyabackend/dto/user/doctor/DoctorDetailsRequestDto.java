package com.star.eswasthyabackend.dto.user.doctor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    private List<Integer> associatedHospitalIdList;

    private String streetAddress;

    private Integer municipalityId;

    public DoctorDetailsRequestDto(Integer userId, String nmcLicenseNo, String phoneNumber, String education,
                                   String specialization, String experience, String gender,
                                   List<Integer> associatedHospitalIdList, String streetAddress, Integer municipalityId){
        this.userId = userId;
        this.nmcLicenseNo = nmcLicenseNo;
        this.phoneNumber = phoneNumber;
        this.education = education;
        this.specialization = specialization;
        this.experience = experience;
        this.gender = gender;
        this.associatedHospitalIdList = associatedHospitalIdList;
        this.streetAddress = streetAddress;
        this.municipalityId = municipalityId;
    }
}
