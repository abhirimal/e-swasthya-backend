package com.star.eswasthyabackend.controller.dashboard;

import com.star.eswasthyabackend.dto.ApiResponse;
import com.star.eswasthyabackend.service.dashboard.AdminDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dashboard/admin")
public class AdminDashboardController {

    private final AdminDashboardService adminDashboardService;

    @GetMapping("/get-patient-doctor-count")
    public ResponseEntity<?> getAdminDashboard(){
        return ResponseEntity.ok(new ApiResponse(true,
                "Doctor and Patient count fetched successfully",
                adminDashboardService.getUsersCount()
        ));
    }

    @GetMapping("/get-disease-count-per-district-by-disease-name")
    public ResponseEntity<?> getDiseaseCountPerDistrict(@RequestParam("diseaseName") String diseaseName){
        return ResponseEntity.ok(new ApiResponse(true,
                "Disease count list per district fetched successfully",
                adminDashboardService.getDiseaseCountPerDistrict(diseaseName)
        ));
    }

    @GetMapping("/get-disease-count-per-district-by-disease-type")
    public ResponseEntity<?> getDiseaseCountPerDistrictByDiseaseType(@RequestParam("diseaseType") String diseaseType){
        return ResponseEntity.ok(new ApiResponse(true,
                "Disease count list per district fetched successfully",
                adminDashboardService.getDiseaseCountPerDistrictByDiseaseType(diseaseType)
        ));
    }

    @GetMapping("/get-disease-count-in-province-by-disease-name")
    public ResponseEntity<?> getDiseaseCountInProvince(@RequestParam Integer provinceId,
                                                       @RequestParam String diseaseName){

        return ResponseEntity.ok(new ApiResponse(
                true,
                "Disease count in a province fetched successfully.",
                adminDashboardService.getDiseaseCountInProvince(provinceId, diseaseName)
        ));
    }

    @GetMapping("/get-disease-count-in-province-by-disease-type")
    public ResponseEntity<?> getDiseaseCountInProvinceByDiseaseType(@RequestParam Integer provinceId,
                                                       @RequestParam String diseaseType){

        return ResponseEntity.ok(new ApiResponse(
                true,
                "Disease count in a province fetched successfully.",
                adminDashboardService.getDiseaseCountInProvinceByDiseaseType(provinceId, diseaseType)
        ));
    }

    @GetMapping("/get-disease-count-per-municipality-by-disease-name")
    public ResponseEntity<?> getDiseaseCountPerMunicipality(@RequestParam Integer districtId,
                                                            @RequestParam String diseaseName){

        return ResponseEntity.ok(new ApiResponse(
                true,
                "Disease count list per municipality fetched successfully.",
                adminDashboardService.getDiseaseCountPerMunicipality(districtId, diseaseName)
        ));
    }

    @GetMapping("/get-disease-count-per-municipality-by-disease-type")
    public ResponseEntity<?> getDiseaseCountPerMunicipalityByDiseaseType(@RequestParam Integer districtId,
                                                            @RequestParam String diseaseType){

        return ResponseEntity.ok(new ApiResponse(
                true,
                "Disease count list per municipality fetched successfully.",
                adminDashboardService.getDiseaseCountPerMunicipalityByDiseaseType(districtId, diseaseType)
        ));
    }

    @GetMapping("/get-medicine-count-per-district-by-medicine-name")
    public ResponseEntity<?> getMedicineCount(@RequestParam("medicineName") String medicineName){

        return ResponseEntity.ok(new ApiResponse(true,
                "Medicine count per district fetched successfully",
                adminDashboardService.getMedicineCount(medicineName)));
    }

    @GetMapping("/get-medicine-count-per-district-by-medicine-type")
    public ResponseEntity<?> getMedicineCountByMedicineType(@RequestParam("medicineType") String medicineType){

        return ResponseEntity.ok(new ApiResponse(true,
                "Medicine count per district fetched successfully",
                adminDashboardService.getMedicineCountPerDistrictByMedicineType(medicineType)));
    }

    @GetMapping("/get-medicine-count-in-province-by-medicine-name")
    public ResponseEntity<?> getMedicineCountInProvince(@RequestParam Integer provinceId,
                                                        @RequestParam String medicineName){

        return ResponseEntity.ok(new ApiResponse(
                true,
                "Medicine count in province fetched successfully.",
                adminDashboardService.getMedicineCountInProvince(provinceId, medicineName)
        ));
    }

    @GetMapping("/get-medicine-count-in-province-by-medicine-type")
    public ResponseEntity<?> getMedicineCountInProvinceByMedicineType(@RequestParam Integer provinceId,
                                                        @RequestParam String medicineType){

        return ResponseEntity.ok(new ApiResponse(
                true,
                "Medicine count in province fetched successfully.",
                adminDashboardService.getMedicineCountInProvinceByMedicineType(provinceId, medicineType)
        ));
    }

    @GetMapping("/get-medicine-count-per-municipality-by-medicine-name")
    public ResponseEntity<?> getMedicineCountPerMunicipality(@RequestParam Integer districtId,
                                                             @RequestParam String medicineName){

        return ResponseEntity.ok(new ApiResponse(
                true,
                "Medicine count list per municipality fetched successfully.",
                adminDashboardService.getMedicineCountPerMunicipality(districtId, medicineName)
        ));
    }

    @GetMapping("/get-medicine-count-per-municipality-by-medicine-type")
    public ResponseEntity<?> getMedicineCountPerMunicipalityByMedicineType(@RequestParam Integer districtId,
                                                             @RequestParam String medicineType){

        return ResponseEntity.ok(new ApiResponse(
                true,
                "Medicine count list per municipality fetched successfully.",
                adminDashboardService.getMedicineCountPerMunicipalityByMedicineType(districtId, medicineType)
        ));
    }

    @GetMapping("/get-vaccination-count-per-district")
    public ResponseEntity<?> getVaccinationCount(@RequestParam("vaccineName") String vaccineName){

        return ResponseEntity.ok(new ApiResponse(true,
                "Vaccination per district count is fetched successfully.",
                adminDashboardService.getVaccinationCount(vaccineName)
        ));
    }

    @GetMapping("/get-vaccination-count-in-province")
    public ResponseEntity<?> getVaccinationCountInProvince(Integer provinceId, String vaccineName){

        return ResponseEntity.ok(new ApiResponse(
                true,
                "Vaccination count in district fetched successfully",
                adminDashboardService.findVaccinationCountInProvince(provinceId, vaccineName)
        ));
    }

    @GetMapping("/get-vaccination-count-per-municipality")
    public ResponseEntity<?> getVaccinationCountListPerMunicipality(@RequestParam Integer districtId,
                                                                    @RequestParam String vaccineName){

        return ResponseEntity.ok(new ApiResponse(
                true,
                "Vaccination count list per municipality fetched successfully.",
                adminDashboardService.getVaccinationCountPerMunicipality(districtId, vaccineName)
        ));
    }
}