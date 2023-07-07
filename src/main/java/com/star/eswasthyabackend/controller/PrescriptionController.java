package com.star.eswasthyabackend.controller;

import com.star.eswasthyabackend.dto.ApiResponse;
import com.star.eswasthyabackend.dto.prescription.PrescriptionRequestDto;
import com.star.eswasthyabackend.service.prescription.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/prescription")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    @PostMapping("/save")
    public ResponseEntity<?> savePrescription(@RequestBody PrescriptionRequestDto requestDto) {

        return ResponseEntity.ok(new ApiResponse(true,
                "Prescription data saved successfully",
                prescriptionService.savePrescription(requestDto)
        ));
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<?> viewPrescriptionById(@PathVariable Integer id) {

        return ResponseEntity.ok(new ApiResponse(true,
                "Prescription data fetched successfully",
                prescriptionService.viewPrescriptionById(id)
        ));
    }

    @GetMapping("/list-medicine-name")
    public ResponseEntity<?> listMedicineName() {

        return ResponseEntity.ok(new ApiResponse(true,
                "Medicine list fetched successfully.",
                prescriptionService.listMedicineNames()
        ));
    }

    @GetMapping("/list-medicine-type")
    public ResponseEntity<?> listMedicineType() {

        return ResponseEntity.ok(new ApiResponse(true,
                "Medicine type list fetched successfully",
                prescriptionService.listMedicineType()
        ));
    }

    @GetMapping("/list-medicine-name-by-type")
    public ResponseEntity<?> listMedicineNameByType(@RequestParam(name = "medicineType") String medicineType) {

        return ResponseEntity.ok(new ApiResponse(
                true,
                "Medicine names fetched successfully",
                prescriptionService.listMedicineNameByMedicineType(medicineType)
        ));
    }

    @GetMapping("/list")
    public ResponseEntity<?> listPrescriptionByPatientId(@RequestParam Integer patientId){

        return ResponseEntity.ok(new ApiResponse(
                true,
                "Prescription list fetched successfully",
                prescriptionService.listPrescriptionByPatientId(patientId)
        ));
    }
}