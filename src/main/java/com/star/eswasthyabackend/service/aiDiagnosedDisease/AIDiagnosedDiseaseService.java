package com.star.eswasthyabackend.service.aiDiagnosedDisease;

import com.star.eswasthyabackend.dto.AIDiagnosedDiseaseRequestDto;

import java.util.Map;
import java.util.Objects;

public interface AIDiagnosedDiseaseService {
    Integer saveAIDiagnosedDisease(AIDiagnosedDiseaseRequestDto requestDto);

    Map<String, Object> getAIDiagnosedDiseaseByAppointmentId(Integer appointmentId);
}
