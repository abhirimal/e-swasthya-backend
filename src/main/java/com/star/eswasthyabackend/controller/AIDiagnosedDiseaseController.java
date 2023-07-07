package com.star.eswasthyabackend.controller;

import com.star.eswasthyabackend.dto.AIDiagnosedDiseaseRequestDto;
import com.star.eswasthyabackend.dto.ApiResponse;
import com.star.eswasthyabackend.service.aiDiagnosedDisease.AIDiagnosedDiseaseService;
import com.star.eswasthyabackend.service.allergy.AllergicMedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/ai-diagnosed-disease")
public class AIDiagnosedDiseaseController {

    private final AIDiagnosedDiseaseService aiDiagnosedDiseaseService;

    @PostMapping("/save")
    public ResponseEntity<?> saveAIDiagnosedDisease(@Valid @RequestBody AIDiagnosedDiseaseRequestDto requestDto){

        return ResponseEntity.ok(new ApiResponse(true,
                "AI diagnosed disease detail saved successfully.",
                aiDiagnosedDiseaseService.saveAIDiagnosedDisease(requestDto)
                ));
    }

    @GetMapping("/get")
    public ResponseEntity<?> getByAppointmentId(@RequestParam Integer appointmentId){

        return ResponseEntity.ok(new ApiResponse(
                true,
                "AI diagnosed disease fetched successfully.",
                aiDiagnosedDiseaseService.getAIDiagnosedDiseaseByAppointmentId(appointmentId)
        ));
    }
}
