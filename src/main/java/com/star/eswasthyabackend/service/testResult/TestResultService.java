package com.star.eswasthyabackend.service.testResult;

import com.star.eswasthyabackend.dto.TestResultRequestDto;

public interface TestResultService {
    Integer saveTestResult(TestResultRequestDto requestDto);
}
