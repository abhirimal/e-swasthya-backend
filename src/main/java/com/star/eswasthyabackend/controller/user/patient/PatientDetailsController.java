package com.star.eswasthyabackend.controller.user.patient;

import com.star.eswasthyabackend.dto.ApiResponse;
import com.star.eswasthyabackend.dto.user.patient.PatientDetailsRequestDto;
import com.star.eswasthyabackend.service.user.patient.PatientDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/patient")
@RequiredArgsConstructor
public class PatientDetailsController {

    private final PatientDetailsService patientDetailsService;
    @PostMapping("/save")
    public ResponseEntity<?> savePatientDetails(@Valid @ModelAttribute PatientDetailsRequestDto requestDto){

        return ResponseEntity.ok(new ApiResponse(true,
                "User details saved successfully.",
                patientDetailsService.savePatientDetails(requestDto)));
    }
}
