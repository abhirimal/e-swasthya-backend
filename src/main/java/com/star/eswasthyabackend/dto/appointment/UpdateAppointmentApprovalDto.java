package com.star.eswasthyabackend.dto.appointment;

import com.star.eswasthyabackend.enums.AppointmentStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class UpdateAppointmentApprovalDto {

    private Integer appointmentId;

    private Integer doctorId;

    private Integer patientId;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;
}
