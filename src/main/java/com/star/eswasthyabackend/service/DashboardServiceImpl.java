package com.star.eswasthyabackend.service;

import com.star.eswasthyabackend.repository.VaccinationRepository;
import com.star.eswasthyabackend.repository.user.UserRepository;
import com.star.eswasthyabackend.repository.user.doctor.DoctorDetailsRepository;
import com.star.eswasthyabackend.repository.user.patient.PatientDetailsRepository;
import com.star.eswasthyabackend.service.user.doctor.DoctorDetailsService;
import com.star.eswasthyabackend.service.user.doctor.DoctorDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService{

    private final UserRepository userRepository;
    private final PatientDetailsRepository patientDetailsRepository;
    private final DoctorDetailsRepository doctorDetailsRepository;
    private final DoctorDetailsService doctorDetailsService;
    private final VaccinationRepository vaccinationRepository;

    @Override
    public Map<String, Object> getAdminDashboard() {

        Map<String, Object> dashboardData = new HashMap<>();
        dashboardData.put("totalPatients", patientDetailsRepository.countTotalPatients());
        dashboardData.put("totalDoctors", doctorDetailsRepository.countTotalDoctors());
        dashboardData.put("allPatientDetail", patientDetailsRepository.listAllPatient());
        dashboardData.put("allDoctorDetail", doctorDetailsRepository.listAllDoctor());
        return dashboardData;
    }

    @Override
    public Map<String, Object> getDoctorDashboard(Integer doctorDetailId) {

        Map<String, Object> doctorDashboardData = new HashMap<>();
        doctorDashboardData.put("doctorDetail", doctorDetailsService.findById(doctorDetailId));
        doctorDashboardData.put("appointmentList", null);
        return doctorDashboardData;
    }

    @Override
    public Map<String, Object> getPatientDashboard(Integer patientDetailId) {

        Map<String, Object> patientDashboardData = new HashMap<>();
        patientDashboardData.put("vaccination", vaccinationRepository.findByPatientId(patientDetailId));
        patientDashboardData.put("patientDetail", patientDetailsRepository.findPatientDetail(patientDetailId));
        return patientDashboardData;
    }
}
