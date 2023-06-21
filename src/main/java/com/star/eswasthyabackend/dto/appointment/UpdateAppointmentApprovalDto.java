package com.star.eswasthyabackend.dto.appointment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAppointmentApprovalDto {

    private Integer appointmentId;

    private Integer doctorId;

    private Integer patientId;

    private Boolean isApproved;
}
