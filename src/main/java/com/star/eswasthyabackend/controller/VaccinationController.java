package com.star.eswasthyabackend.controller;

import com.star.eswasthyabackend.dto.ApiResponse;
import com.star.eswasthyabackend.dto.vaccination.VaccinationRequest;
import com.star.eswasthyabackend.service.vaccination.VaccinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/vaccination")
@RequiredArgsConstructor
public class VaccinationController {

    private final VaccinationService vaccinationService;

    @PostMapping("/save")
    public ResponseEntity<?> saveVaccination(@Valid @RequestBody VaccinationRequest vaccinationRequest){

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

    @GetMapping("/get-vaccine-name-list")
    public ResponseEntity<?> getVaccineNameList(){

        return ResponseEntity.ok(new ApiResponse(true,
                "Vaccines list fetched successfully", vaccinationService.listVaccines()
        ));
    }
}