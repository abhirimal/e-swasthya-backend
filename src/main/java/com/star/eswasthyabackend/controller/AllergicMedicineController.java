package com.star.eswasthyabackend.controller;

import com.star.eswasthyabackend.dto.ApiResponse;
import com.star.eswasthyabackend.dto.allergy.AllergicMedicineRequestDto;
import com.star.eswasthyabackend.service.allergy.AllergicMedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/allergic-medicine")
public class AllergicMedicineController {

    private final AllergicMedicineService allergicMedicineService;

    @PostMapping("/save")
    public ResponseEntity<?> saveAllergy(@RequestBody AllergicMedicineRequestDto allergicMedicineRequestDto) {

        return ResponseEntity.ok(new ApiResponse(true,
                "AllergicMedicine saved successfully",
                allergicMedicineService.saveAllergyList(allergicMedicineRequestDto)
                )
        );
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllergicMedicineListByPatientId(@RequestParam Integer patientId){

        return ResponseEntity.ok(new ApiResponse(true,
                "Allergic medicines list fetched successfully.",
                allergicMedicineService.listAllergicMedicineByPatientId(patientId)
        ));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAllergyById(@RequestParam Integer allergyId){

        return ResponseEntity.ok(new ApiResponse(
                true,
                "Allergic medicine deleted successfully",
                allergicMedicineService.deleteById(allergyId)
        ));
    }
}
