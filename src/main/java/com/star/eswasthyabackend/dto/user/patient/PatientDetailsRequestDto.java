package com.star.eswasthyabackend.dto.user.patient;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class PatientDetailsRequestDto {

    private Integer id;

    private Integer userId;

    private String citizenshipNo;

    private Integer medicalRecordId;

    private String phoneNumber;

    private String weight;

    private String bloodGroup;

    private String address;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    }
