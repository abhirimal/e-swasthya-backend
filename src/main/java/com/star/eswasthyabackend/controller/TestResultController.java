package com.star.eswasthyabackend.controller;

import com.star.eswasthyabackend.dto.ApiResponse;
import com.star.eswasthyabackend.dto.testResult.TestResultRequestDto;
import com.star.eswasthyabackend.service.testResult.TestResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test-result")
public class TestResultController {

    private final TestResultService testResultService;

    @PostMapping("/save")
    public ResponseEntity<?> saveTestResult(@RequestBody TestResultRequestDto requestDto){

        return ResponseEntity.ok(new ApiResponse(true,
                "Test Result Saved Successfully",
                testResultService.saveTestResult(requestDto)));
    }

    @GetMapping("/view/{testResultId}")
    public ResponseEntity<?> viewTestResultById(@PathVariable Integer testResultId){

        return ResponseEntity.ok(new ApiResponse(true,
                "Test result data fetched successfully.",
                testResultService.findById(testResultId)));
    }

    @GetMapping("/view-all/{patientId}")
    public ResponseEntity<?> listAllAppointmentsByPatientId(@PathVariable Integer patientId){

        return ResponseEntity.ok(new ApiResponse(true,
                "List of test results fetched successfully.",
                testResultService.findAllByPatientId(patientId)));
    }
}
