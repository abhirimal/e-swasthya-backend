package com.star.eswasthyabackend.controller;

import com.star.eswasthyabackend.dto.ApiResponse;
import com.star.eswasthyabackend.dto.TestResultRequestDto;
import com.star.eswasthyabackend.service.testResult.TestResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
