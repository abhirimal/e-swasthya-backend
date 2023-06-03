package com.star.eswasthyabackend.service.user.appointment;

import com.star.eswasthyabackend.dto.appointment.AppointmentRequest;

import java.util.List;
import java.util.Map;

public interface AppointmentService {
    Integer saveAndUpdate(AppointmentRequest appointmentRequest);

    Map<String, Object> viewById(Integer appointmentId);

    List<Map<String, Object>> viewByDoctorid(Integer doctorId);

    List<Map<String, Object>> viewByPatiendId(Integer patientId);

    Boolean deleteByAppointmentById(Integer appointmentId);
}
