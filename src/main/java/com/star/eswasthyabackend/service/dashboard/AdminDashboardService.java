package com.star.eswasthyabackend.service.dashboard;

import java.util.List;
import java.util.Map;

public interface AdminDashboardService {
    Map<String, Object> getUsersCount();

    List<Map<String, Object>> getDiseaseCount(String diseaseName);
}
