package com.star.eswasthyabackend.controller;

import com.star.eswasthyabackend.dto.ApiResponse;
import com.star.eswasthyabackend.dto.diagnosis.DiagnosisRequestDto;
import com.star.eswasthyabackend.service.diagnosis.DiagnosisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/diagnosis")
public class DiagnosisController {

    private final DiagnosisService diagnosisService;

    @PostMapping("/save")
    public ResponseEntity<?> saveDiagnosis(@RequestBody DiagnosisRequestDto requestDto){

        return ResponseEntity.ok(new ApiResponse(true,
                "Diagnosis saved successfully.",
                diagnosisService.saveDiagnosis(requestDto)
        ));
    }


}
