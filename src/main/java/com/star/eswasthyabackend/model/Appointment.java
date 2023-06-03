package com.star.eswasthyabackend.model;

import com.star.eswasthyabackend.model.doctor.DoctorDetails;
import com.star.eswasthyabackend.model.patient.PatientDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String hospitalName;

    private String reasonForVisit;

    @OneToOne
    @JoinColumn(name = "patient_id")
    private PatientDetails patientDetails;

    @OneToOne
    @JoinColumn(name = "doctor_id")
    private DoctorDetails doctorDetails;

    private LocalDate appointmentDate;

    private LocalTime appointmentTime;

    private Boolean isActive;
}
