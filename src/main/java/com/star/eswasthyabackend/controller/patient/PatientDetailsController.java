package com.star.eswasthyabackend.controller.patient;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/patient")
@RequiredArgsConstructor
public class PatientDetailsController {

    @PostMapping("/save")
    public ResponseEntity<?> savePatientDetails(){

        return null;
    }
}
