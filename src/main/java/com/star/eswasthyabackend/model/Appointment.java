package com.star.eswasthyabackend.model;

import com.star.eswasthyabackend.enums.AppointmentStatus;
import com.star.eswasthyabackend.model.doctor.DoctorDetails;
import com.star.eswasthyabackend.model.location.Location;
import com.star.eswasthyabackend.model.patient.PatientDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    private String reasonForVisit;

    private LocalDate appointmentDate;

    private LocalTime appointmentTime;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    private String otpCode;

    private LocalTime otpGenTime;

    private Boolean isDiagnosisFilled;

    @OneToOne
    @JoinColumn(name = "patient_detail_id")
    private PatientDetails patientDetails;

    @OneToOne
    @JoinColumn(name = "doctor_detail_id")
    private DoctorDetails doctorDetails;

    @OneToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;
}
