package com.star.eswasthyabackend.service.diagnosis;

import com.star.eswasthyabackend.dto.diagnosis.DiagnosisRequestDto;

import java.util.Map;

public interface DiagnosisService {
    Integer saveDiagnosis(DiagnosisRequestDto requestDto);

    Map<String, Object> getDiagnosisById(Integer id);
}
