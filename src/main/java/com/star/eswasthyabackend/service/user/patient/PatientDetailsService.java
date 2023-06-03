package com.star.eswasthyabackend.service.user.patient;

import com.star.eswasthyabackend.dto.user.patient.PatientDetailsRequestDto;

import java.util.List;
import java.util.Map;

public interface PatientDetailsService {
    Integer savePatientDetails(PatientDetailsRequestDto requestDto);
    Map<String, String> getPatientDetails(Integer id);

    List<Map<String, String>> getAll();
}
