package com.star.eswasthyabackend.dto.user.doctor;

import com.star.eswasthyabackend.dto.HospitalResponseDto;
import com.star.eswasthyabackend.model.Hospital;
import com.star.eswasthyabackend.model.doctor.DoctorDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDetailResponseDto {

    private Integer userId;

    private Integer doctorDetailId;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String imagePath;

    private String nmcLicenseNo;

    private String education;

    private String specialization;

    private String experience;

    private String gender;

    private List<HospitalResponseDto> associatedhospitalList;

    public DoctorDetailResponseDto(DoctorDetails doctorDetails){
        this.userId = doctorDetails.getUser().getId();
        this.doctorDetailId = doctorDetails.getDoctorDetailId();
        this.firstName = doctorDetails.getFirstName();
        this.lastName = doctorDetails.getLastName();
        this.email = doctorDetails.getEmail();
        this.phoneNumber = doctorDetails.getPhoneNumber();
        this.imagePath = doctorDetails.getImagePath();
        this.nmcLicenseNo = doctorDetails.getNmcLicenseNo();
        this.education = doctorDetails.getEducation();
        this.specialization = doctorDetails.getSpecialization();
        this.experience = doctorDetails.getExperience();
        this.gender = doctorDetails.getGender();
        this.associatedhospitalList = doctorDetails.getAssociatedHospitalList()
                .stream()
                .map(hospital -> new HospitalResponseDto(hospital.getId(), hospital.getHospitalName(), hospital.getLocation().getId()))
                .collect(Collectors.toList());
    }
}
