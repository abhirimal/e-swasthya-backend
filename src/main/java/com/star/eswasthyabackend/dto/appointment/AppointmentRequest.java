package com.star.eswasthyabackend.dto.appointment;

import com.star.eswasthyabackend.model.doctor.DoctorDetails;
import com.star.eswasthyabackend.model.patient.PatientDetails;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class AppointmentRequest {

    private Integer id;

    @NotEmpty(message = "Reason for visit cannot be empty")
    private String reasonForVisit;

    @NotNull(message = "Patient id cannot be empty")
    private Integer patientDetailId;

    @NotNull(message = "Doctor id cannot be empty")
    private Integer doctorDetailId;

    @NotNull(message = "Appointment date cannot be empty")
    private LocalDate appointmentDate;

    @NotNull(message = "Appointment time cannot be empty")
    private LocalTime appointmentTime;

    @NotNull(message = "Hospital id cannot be empty")
    private Integer hospitalId;
}
