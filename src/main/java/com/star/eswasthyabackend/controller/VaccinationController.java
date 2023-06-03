package com.star.eswasthyabackend.controller;

import com.star.eswasthyabackend.dto.VaccinationRequest;
import com.star.eswasthyabackend.service.user.vaccination.VaccinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vaccination")
@RequiredArgsConstructor
public class VaccinationController {

    private final VaccinationService vaccinationService;

    @PostMapping("/save")
    public ResponseEntity<?> saveVaccination(@RequestBody VaccinationRequest vaccinationRequest){

        return null;
    }
}
