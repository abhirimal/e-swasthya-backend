package com.star.eswasthyabackend.service.dashboard;

import java.util.List;
import java.util.Map;

public interface AdminDashboardService {
    Map<String, Object> getUsersCount();

    List<Map<String, Object>> getDiseaseCount(String diseaseName);

    List<Map<String, Object>> getMedicineCount(String medicineName);

    List<Map<String, Object>> getVaccinationCount(String vaccineName);
}
