package com.star.eswasthyabackend.service.user.appointment;

import com.star.eswasthyabackend.dto.appointment.AppointmentRequest;

import java.util.List;
import java.util.Map;

public interface AppointmentService {
    Integer saveAndUpdate(AppointmentRequest appointmentRequest);

    Map<String, Object> viewById(Integer appointmentId);

    List<Map<String, Object>> viewByDoctorId(Integer doctorId);

    List<Map<String, Object>> viewByPatientdId(Integer patientId);

    Boolean deleteByAppointmentById(Integer appointmentId);
}
