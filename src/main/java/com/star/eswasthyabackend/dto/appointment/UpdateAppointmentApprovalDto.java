package com.star.eswasthyabackend.dto.appointment;

import com.star.eswasthyabackend.enums.AppointmentStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateAppointmentApprovalDto {

    @NotNull(message = "Appointment Id cannot be empty")
    private Integer appointmentId;

    @NotNull(message = "Doctor Id cannot be empty")
    private Integer doctorId;

    @NotNull(message = "Patient Id cannot be empty")
    private Integer patientId;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;
}
