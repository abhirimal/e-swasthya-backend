package com.star.eswasthyabackend.service.user.patient;

import com.star.eswasthyabackend.dto.user.patient.PatientDetailsRequestDto;
import com.star.eswasthyabackend.dto.user.patient.UpdateHeightWeightRequestPojo;

import java.util.List;
import java.util.Map;

public interface PatientDetailsService {
    String savePatientDetails(PatientDetailsRequestDto requestDto);

    Map<String, String> getPatientDetails(Integer id);

    List<Map<String, String>> getAll();

    Map<String, Object> getPatientDetailsByUserId(Integer id);

    Boolean updateHeightAndWeight(UpdateHeightWeightRequestPojo requestPojo);
}
