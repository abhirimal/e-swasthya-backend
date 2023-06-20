package com.star.eswasthyabackend.service.diagnosis;

import com.star.eswasthyabackend.dto.diagnosis.DiagnosisRequestDto;

import java.util.List;
import java.util.Map;

public interface DiagnosisService {
    Integer saveDiagnosis(DiagnosisRequestDto requestDto);

    Map<String, Object> getDiagnosisById(Integer id);

    List<Map<String,Object>> listAllByPatientId(Integer id);
}
