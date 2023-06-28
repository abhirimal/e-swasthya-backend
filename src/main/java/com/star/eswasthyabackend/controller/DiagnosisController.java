package com.star.eswasthyabackend.controller;

import com.star.eswasthyabackend.dto.ApiResponse;
import com.star.eswasthyabackend.dto.diagnosis.DiagnosisRequestDto;
import com.star.eswasthyabackend.dto.diagnosis.DiagnosisTestResultPrescriptionRequestDto;
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

    @PostMapping("/save-diagnosis-test-result-prescription")
    public ResponseEntity<?> saveDiagnosisTestResultAndPrescription(@RequestBody DiagnosisTestResultPrescriptionRequestDto requestDto){

        return ResponseEntity.ok(new ApiResponse(true,
                "Data saved successfully",
                diagnosisService.saveDiagnosisTestResultAndPrescription(requestDto)
        ));
    }


    @PostMapping("/save")
    public ResponseEntity<?> saveDiagnosis(@RequestBody DiagnosisRequestDto requestDto){

        return ResponseEntity.ok(new ApiResponse(true,
                "Diagnosis saved successfully.",
                diagnosisService.saveDiagnosis(requestDto)
        ));
    }

    @GetMapping("/get-diagnosis-test-prescription/{appointmentId}")
    public ResponseEntity<?> viewDiagnosisByAppointmentId(@PathVariable Integer appointmentId){

        return ResponseEntity.ok(new ApiResponse(true,
                "All data fetched successfully.",
                diagnosisService.viewDiagnosisByAppointmentId(appointmentId)
        ));
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<?> viewByDiagnosisById(@PathVariable Integer id){

        return ResponseEntity.ok(new ApiResponse(true,
                "Diagnosis data fetched successfully",
                diagnosisService.getDiagnosisById(id)));
    }

    @GetMapping("/list-by-patient/{id}")
    public ResponseEntity<?> listALlByPatientId(@PathVariable Integer id){

        return ResponseEntity.ok(new ApiResponse(true,
                "Diagnosis list fetched successfully", diagnosisService.listAllByPatientId(id)
        ));
    }

    @GetMapping("/list-by-doctor/{id}")
    public ResponseEntity<?> listALlByDoctorId(@PathVariable Integer id){

        return ResponseEntity.ok(new ApiResponse(true,
                "Diagnosis list fetched successfully", diagnosisService.listAllByDoctorId(id)
        ));
    }
}
