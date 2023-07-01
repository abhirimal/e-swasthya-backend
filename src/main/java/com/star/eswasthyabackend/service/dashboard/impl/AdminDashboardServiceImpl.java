package com.star.eswasthyabackend.service.dashboard.impl;

import com.star.eswasthyabackend.repository.DiagnosisRepository;
import com.star.eswasthyabackend.repository.PrescriptionRepository;
import com.star.eswasthyabackend.repository.VaccinationRepository;
import com.star.eswasthyabackend.repository.user.doctor.DoctorDetailsRepository;
import com.star.eswasthyabackend.repository.user.patient.PatientDetailsRepository;
import com.star.eswasthyabackend.service.dashboard.AdminDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminDashboardServiceImpl implements AdminDashboardService {

    private final PatientDetailsRepository patientDetailsRepository;
    private final DoctorDetailsRepository doctorDetailsRepository;
    private final DiagnosisRepository diagnosisRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final VaccinationRepository vaccinationRepository;

    @Override
    public Map<String, Object> getUsersCount() {
        Map<String, Object> dashboardData = new HashMap<>();
        dashboardData.put("totalPatients", patientDetailsRepository.countTotalPatients());
        dashboardData.put("totalDoctors", doctorDetailsRepository.countTotalDoctors());
        return dashboardData;
    }

    @Override
    public List<Map<String, Object>> getDiseaseCount(String diseaseName) {
        return diagnosisRepository.getDiseaseCountByDistrict(diseaseName);
    }

    @Override
    public List<Map<String, Object>> getMedicineCount(String medicineName) {
        return prescriptionRepository.findMedicineCount(medicineName);
    }

    @Override
    public List<Map<String, Object>> getVaccinationCount(String vaccineName) {
        return vaccinationRepository.findVaccinationCount(vaccineName);
    }
}