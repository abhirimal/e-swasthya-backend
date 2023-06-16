package com.star.eswasthyabackend.service;

import java.util.Map;

public interface DashboardService {

    Map<String, Object> getAdminDashboard();

    Map<String, Object> getDoctorDashboard(Integer doctorDetailId);

    Map<String, Object> getPatientDashboard(Integer patientDetailId);

}
