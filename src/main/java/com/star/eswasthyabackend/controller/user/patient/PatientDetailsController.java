package com.star.eswasthyabackend.controller.user.patient;

import com.star.eswasthyabackend.dto.ApiResponse;
import com.star.eswasthyabackend.dto.JwtResponse;
import com.star.eswasthyabackend.dto.user.patient.PatientDetailsRequestDto;
import com.star.eswasthyabackend.service.user.patient.PatientDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/patient")
@RequiredArgsConstructor
public class PatientDetailsController {

    private final PatientDetailsService patientDetailsService;

    @PostMapping("/save")
    public ResponseEntity<?> savePatientDetails(@Valid @RequestBody PatientDetailsRequestDto requestDto) {
        String jwt = patientDetailsService.savePatientDetails(requestDto);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<?> viewPatientDetail(@PathVariable Integer id) {

        return ResponseEntity.ok(new ApiResponse(true,
                "Patient details fetched successfully.",
                patientDetailsService.getPatientDetails(id)));
    }

    @GetMapping("/view-by-user-id/{id}")
    public ResponseEntity<?> viewPatientDetailByUserId(@PathVariable Integer id) {

        return ResponseEntity.ok(new ApiResponse(true,
                "Patient details fetched successfully.",
                patientDetailsService.getPatientDetailsByUserId(id)));
    }

    @GetMapping("/view-all")
    public ResponseEntity<?> viewAllPatients() {
        return ResponseEntity.ok(new ApiResponse(true,
                "Patients list fetched successfully.",
                patientDetailsService.getAll()));
    }
}
