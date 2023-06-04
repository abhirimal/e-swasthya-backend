package com.star.eswasthyabackend.controller;

import com.star.eswasthyabackend.dto.ApiResponse;
import com.star.eswasthyabackend.dto.VaccinationRequest;
import com.star.eswasthyabackend.service.user.vaccination.VaccinationService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.security.SecurityConfig;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vaccination")
@RequiredArgsConstructor
public class VaccinationController {

    private final VaccinationService vaccinationService;

    @PostMapping("/save")
    public ResponseEntity<?> saveVaccination(@RequestBody VaccinationRequest vaccinationRequest){

        return ResponseEntity.ok(new ApiResponse(true,
                "Vaccination report saved successfully.",
                vaccinationService.saveVaccinationReport(vaccinationRequest)));
    }

    @GetMapping("/view/{vaccinationId}")
    public ResponseEntity<?> viewVaccinationReport(@PathVariable Integer vaccinationId){

        return ResponseEntity.ok(new ApiResponse(true,
                "Vaccination report fetched successfully.",
                vaccinationService.viewById(vaccinationId)));

    }

    @GetMapping("/view-all")
    public ResponseEntity<?> viewAllVaccination(){

        return ResponseEntity.ok(new ApiResponse(
                true,
                "All vaccination reports fetched successfully,",
                vaccinationService.viewAllVaccinationReport()
        ));
    }

    @GetMapping("/view-by-patient/{patientId}")
    public ResponseEntity<?> viewByPatientId(@PathVariable Integer patientId){

        return ResponseEntity.ok(new ApiResponse(
                true,
                "Vaccionation Reports feteched successfully.",
                vaccinationService.findByPatientId(patientId)
        ));
    }


}
