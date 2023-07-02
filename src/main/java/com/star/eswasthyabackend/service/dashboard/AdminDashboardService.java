package com.star.eswasthyabackend.service.dashboard;

import java.util.List;
import java.util.Map;

public interface AdminDashboardService {
    Map<String, Object> getUsersCount();

    List<Map<String, Object>> getDiseaseCountPerDistrict(String diseaseName);

    List<Map<String, Object>> getMedicineCount(String medicineName);

    List<Map<String, Object>> getVaccinationCount(String vaccineName);

    Map<String, String> getDiseaseCountInProvince(Integer provinceId, String diseaseName);

    Map<String, Object> getDiseaseCountPerMunicipality(Integer districtId, String diseaseName);

    Map<String, String> getMedicineCountInProvince(Integer provinceId, String medicineName);

    Map<String, Object> getMedicineCountPerMunicipality(Integer districtId, String medicineName);

    Map<String, Object> findVaccinationCountInProvince(Integer provinceId, String vaccineName);

    Map<String, Object> getVaccinationCountPerMunicipality(Integer districtId, String vaccineName);
}
