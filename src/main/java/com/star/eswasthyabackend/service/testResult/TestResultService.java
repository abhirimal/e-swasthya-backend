package com.star.eswasthyabackend.service.testResult;

import com.star.eswasthyabackend.dto.testResult.TestResultRequestDto;
import com.star.eswasthyabackend.dto.testResult.TestResultResponseDto;

import java.util.List;
import java.util.Map;

public interface TestResultService {
    Integer saveTestResult(TestResultRequestDto requestDto);

    TestResultResponseDto findById(Integer testResultId);

    List<Map<String, Object>> findAllByPatientId(Integer patientId);
}
