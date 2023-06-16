package com.star.eswasthyabackend.service.appointment;

import com.star.eswasthyabackend.dto.appointment.AppointmentRequest;

import java.util.List;
import java.util.Map;

public interface AppointmentService {
    String saveAndUpdate(AppointmentRequest appointmentRequest);

    Map<String, Object> viewById(Integer appointmentId);

    List<Map<String, Object>> viewByDoctorId(Integer doctorId);

    List<Map<String, Object>> viewByPatientdId(Integer patientId);

    Boolean deleteByAppointmentById(Integer appointmentId);
}
