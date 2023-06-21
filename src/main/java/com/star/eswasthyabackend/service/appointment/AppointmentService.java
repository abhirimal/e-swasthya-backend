package com.star.eswasthyabackend.service.appointment;

import com.star.eswasthyabackend.dto.appointment.AppointmentRequest;
import com.star.eswasthyabackend.dto.appointment.UpdateAppointmentApprovalDto;

import java.util.List;
import java.util.Map;

public interface AppointmentService {
    String save(AppointmentRequest appointmentRequest);

    Map<String, Object> viewById(Integer appointmentId);

    List<Map<String, Object>> viewByDoctorId(Integer doctorId);

    List<Map<String, Object>> viewByPatientId(Integer patientId);

    Boolean deleteByAppointmentById(Integer appointmentId);

    Boolean updateAppointmentApproval(UpdateAppointmentApprovalDto approvalDto);

    Boolean verifyAppointmentByOtp(Integer appointmentId, String otp);
}
