package com.star.eswasthyabackend.controller;

import com.star.eswasthyabackend.dto.ApiResponse;
import com.star.eswasthyabackend.dto.medication.PrescriptionRequestDto;
import com.star.eswasthyabackend.service.prescription.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/prescription")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    @PostMapping("/save")
    public ResponseEntity<?> savePrescription(@RequestBody PrescriptionRequestDto requestDto){

        return ResponseEntity.ok(new ApiResponse(true,
                "Prescription data saved successfully",
                prescriptionService.savePrescription(requestDto)
        ));
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<?> viewPrescriptionById(@PathVariable Integer id){

        return ResponseEntity.ok(new ApiResponse(true,
                "Prescription data fetched successfully",
                prescriptionService.viewPrescriptionById(id)
        ));
    }

}
