package com.star.eswasthyabackend.service.dashboard.impl;

import com.star.eswasthyabackend.repository.diagnosis.DiagnosisRepository;
import com.star.eswasthyabackend.repository.prescription.PrescriptionRepository;
import com.star.eswasthyabackend.repository.vaccination.VaccinationRepository;
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
    public List<Map<String, Object>> getDiseaseCountPerDistrict(String diseaseName) {
        return diagnosisRepository.getDiseaseCountPerDistrict(diseaseName);
    }

    @Override
    public List<Map<String, Object>> getMedicineCount(String medicineName) {
        return prescriptionRepository.findMedicineCount(medicineName);
    }

    @Override
    public List<Map<String, Object>> getVaccinationCount(String vaccineName) {
        return vaccinationRepository.findVaccinationCount(vaccineName);
    }

    @Override
    public Map<String, String> getDiseaseCountInProvince(Integer provinceId, String diseaseName) {
        return diagnosisRepository.getDiseaseCountInProvince(provinceId, diseaseName);
    }

    @Override
    public Map<String, Object> getDiseaseCountPerMunicipality(Integer districtId, String diseaseName) {

        Map<String, Object> totaDiseaseCountInDistrict = diagnosisRepository
                .totalDiseaseCountInDistrict(districtId ,diseaseName);

        List<Map<String, Object>> totalDiseaseCountPerMunicipality = diagnosisRepository
                .totalDiseaseCountPerMunicipality(districtId, diseaseName);

        Map<String, Object> totalDiseaseCount = new HashMap<>();
        totalDiseaseCount.put("districtData", totaDiseaseCountInDistrict);
        totalDiseaseCount.put("municipalityList", totalDiseaseCountPerMunicipality);

        return totalDiseaseCount;
    }

    @Override
    public Map<String, String> getMedicineCountInProvince(Integer provinceId, String medicineName) {
        return prescriptionRepository.getMedicineCountInProvince(provinceId, medicineName);
    }

    @Override
    public Map<String, Object> getMedicineCountPerMunicipality(Integer districtId, String medicineName) {

        Map<String, Object> totalMedicineCountInDistrict = prescriptionRepository
                .getTotalMedicineCountInDistrict(districtId, medicineName);
        List<Map<String, Object>> medicineCountListPerMunicipality = prescriptionRepository
                .getMedicineCountListPerMunicipality(districtId, medicineName);

        Map<String, Object> totalMedicineCount = new HashMap<>();
        totalMedicineCount.put("districtData", totalMedicineCountInDistrict);
        totalMedicineCount.put("municipalityList", medicineCountListPerMunicipality);

        return totalMedicineCount;
    }

    @Override
    public Map<String, Object> findVaccinationCountInProvince(Integer provinceId, String vaccineName) {
        return vaccinationRepository.getVaccinationCountInProvince(provinceId, vaccineName);
    }

    @Override
    public Map<String, Object> getVaccinationCountPerMunicipality(Integer districtId, String vaccineName) {

        Map<String, Object> vaccinationCountInDistrict = vaccinationRepository
                .getVaccinationCountInDistrict(districtId, vaccineName);
        List<Map<String, Object>> vaccinationCountPerMunicipality = vaccinationRepository
                .getVaccinationCountPerMunicipality(districtId, vaccineName);

        Map<String, Object> totalVaccinationCount = new HashMap<>();
        totalVaccinationCount.put("districtData", vaccinationCountInDistrict);
        totalVaccinationCount.put("municipalityList", vaccinationCountPerMunicipality);
        return totalVaccinationCount;
    }

    @Override
    public List<Map<String, Object>> getDiseaseCountPerDistrictByDiseaseType(String diseaseType) {
        return diagnosisRepository.getDiseaseCountPerDistrictByDiseaseType(diseaseType);
    }

    @Override
    public Map<String, Object> getDiseaseCountInProvinceByDiseaseType(Integer provinceId, String diseaseType) {
        return diagnosisRepository.getDiseaseCountInProvinceByDiseaseType(provinceId, diseaseType);
    }

    @Override
    public Map<String, Object> getDiseaseCountPerMunicipalityByDiseaseType(Integer districtId, String diseaseType) {
        Map<String, Object> diseaseCountInDistrict = diagnosisRepository
                .getDiseaseCountInDistrictByType(districtId, diseaseType);
        List<Map<String, Object>> diseaseCountPerMunicipality = diagnosisRepository
                .getDiseaseCountPerMunicipalityByType(districtId, diseaseType);

        Map<String, Object> totalDiseaseCount = new HashMap<>();
        totalDiseaseCount.put("districtData", diseaseCountInDistrict);
        totalDiseaseCount.put("municipalityList", diseaseCountPerMunicipality);
        return totalDiseaseCount;
    }

    @Override
    public List<Map<String, Object>> getMedicineCountPerDistrictByMedicineType(String medicineType) {
        return prescriptionRepository.getMedicineCountInDistrictByMedicineType(medicineType);
    }

    @Override
    public Map<String, Object> getMedicineCountInProvinceByMedicineType(Integer provinceId, String medicineType) {
        return prescriptionRepository.getMedicineCountInProvinceByMedicineType(provinceId, medicineType);
    }

    @Override
    public Map<String, Object> getMedicineCountPerMunicipalityByMedicineType(Integer districtId, String medicineType) {
        Map<String, Object> totalMedicineCountInDistrictByType = prescriptionRepository
                .getTotalMedicineCountInDistrictByType(districtId, medicineType);
        List<Map<String, Object>> medicineCountListPerMunicipalityByType = prescriptionRepository
                .getMedicineCountListPerMunicipalityByType(districtId, medicineType);

        Map<String, Object> totalMedicineCountByType = new HashMap<>();
        totalMedicineCountByType.put("districtData", totalMedicineCountInDistrictByType);
        totalMedicineCountByType.put("municipalityList", medicineCountListPerMunicipalityByType);

        return totalMedicineCountByType;
    }
}
