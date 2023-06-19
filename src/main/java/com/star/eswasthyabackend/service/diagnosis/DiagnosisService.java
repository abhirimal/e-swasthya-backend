package com.star.eswasthyabackend.service.diagnosis;

import com.star.eswasthyabackend.dto.diagnosis.DiagnosisRequestDto;

public interface DiagnosisService {
    Integer saveDiagnosis(DiagnosisRequestDto requestDto);
}
