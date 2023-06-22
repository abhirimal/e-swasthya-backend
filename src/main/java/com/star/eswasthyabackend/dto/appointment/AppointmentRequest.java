package com.star.eswasthyabackend.dto.appointment;

import com.star.eswasthyabackend.model.doctor.DoctorDetails;
import com.star.eswasthyabackend.model.patient.PatientDetails;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class AppointmentRequest {

    private Integer id;

    private String reasonForVisit;

    private Integer patientDetailId;

    private Integer doctorDetailId;

    private LocalDate appointmentDate;

    private LocalTime appointmentTime;

    private Integer hospitalId;
}
