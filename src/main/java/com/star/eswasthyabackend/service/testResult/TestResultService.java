package com.star.eswasthyabackend.service.testResult;

import com.star.eswasthyabackend.dto.testResult.TestResultRequestDto;

import java.util.Map;

public interface TestResultService {
    Integer saveTestResult(TestResultRequestDto requestDto);

    Map<String, Object> findById(Integer testResultId);
}
