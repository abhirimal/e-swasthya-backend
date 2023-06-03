package com.star.eswasthyabackend.model.doctor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer doctorDetailId;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String nmcLicenseNo;

    private String education;

    private String specialization;

    private String experience;

    private String gender;

    private String associatedHospital;

    private String location;
}
