package com.star.eswasthyabackend.controller.user.patient;

import com.star.eswasthyabackend.dto.ApiResponse;
import com.star.eswasthyabackend.dto.user.patient.PatientDetailsRequestDto;
import com.star.eswasthyabackend.service.user.patient.PatientDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/patient")
@RequiredArgsConstructor
public class PatientDetailsController {

    private final PatientDetailsService patientDetailsService;
    @PostMapping("/save")
    public ResponseEntity<?> savePatientDetails(@Valid @RequestBody PatientDetailsRequestDto requestDto){

        return ResponseEntity.ok(new ApiResponse(true,
                "Patient details saved successfully.",
                patientDetailsService.savePatientDetails(requestDto)));
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<?> viewPatientDetail(@PathVariable Integer id){

        return ResponseEntity.ok(new ApiResponse(true,
                "Patient details fetched successfully.",
                patientDetailsService.getPatientDetails(id)));
    }

    @GetMapping("/view-all")
    public ResponseEntity<?> viewAllPatients(){
        return ResponseEntity.ok(new ApiResponse(true,
                "Patients list fetched successfully.",
                patientDetailsService.getAll()));
    }
}
