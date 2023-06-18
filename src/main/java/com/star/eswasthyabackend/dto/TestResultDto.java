package com.star.eswasthyabackend.dto;

import com.star.eswasthyabackend.model.doctor.DoctorDetails;
import com.star.eswasthyabackend.model.patient.PatientDetails;

import javax.persistence.*;
import java.time.LocalDate;

public class TestResultDto {

    private Integer id;
    private String testName;
    private String result;
    private String description;
    private LocalDate testDate;
    private Integer patientDetailId;
    private Integer recommendedDoctorDetailId;
}
