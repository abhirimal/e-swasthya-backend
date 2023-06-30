package com.star.eswasthyabackend.service.appointment;

import com.star.eswasthyabackend.dto.appointment.AppointmentRequest;
import com.star.eswasthyabackend.dto.appointment.UpdateAppointmentApprovalDto;
import com.star.eswasthyabackend.dto.temp.IdSmsDto;

import java.util.List;
import java.util.Map;

public interface AppointmentService {
    IdSmsDto save(AppointmentRequest appointmentRequest);

    Map<String, Object> viewById(Integer appointmentId);

    List<Map<String, Object>> viewByDoctorId(Integer doctorId, String status);

    List<Map<String, Object>> viewByPatientId(Integer patientId, String status);

    Boolean deleteByAppointmentById(Integer appointmentId);

    Boolean updateAppointmentApproval(UpdateAppointmentApprovalDto approvalDto);

    Boolean verifyAppointmentByOtp(Integer appointmentId, String otp);

    IdSmsDto resendOTP(UpdateAppointmentApprovalDto approvalDto);

    List<Map<String, Object>> getAppointmentCount(Integer doctorDetailId);
}
