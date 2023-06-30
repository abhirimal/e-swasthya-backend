package com.star.eswasthyabackend.controller;

import com.star.eswasthyabackend.dto.ApiResponse;
import com.star.eswasthyabackend.dto.allergy.AllergicMedicineRequestDto;
import com.star.eswasthyabackend.service.allergy.AllergicMedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
