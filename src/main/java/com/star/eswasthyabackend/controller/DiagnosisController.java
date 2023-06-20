package com.star.eswasthyabackend.controller;

import com.star.eswasthyabackend.dto.ApiResponse;
import com.star.eswasthyabackend.dto.diagnosis.DiagnosisRequestDto;
import com.star.eswasthyabackend.service.diagnosis.DiagnosisService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;

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

    @GetMapping("/view/{id}")
    public ResponseEntity<?> viewByDiagnosisById(@PathVariable Integer id){

        return ResponseEntity.ok(new ApiResponse(true,
                "Diagnosis data fetched successfully",
                diagnosisService.getDiagnosisById(id)));
    }


}
