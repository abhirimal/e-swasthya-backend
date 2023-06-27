package com.star.eswasthyabackend.service.diagnosis;

import com.star.eswasthyabackend.dto.diagnosis.DiagnosisRequestDto;
import com.star.eswasthyabackend.dto.diagnosis.DiagnosisTestResultPrescriptionRequestDto;

import java.util.List;
import java.util.Map;

public interface DiagnosisService {
    Integer saveDiagnosis(DiagnosisRequestDto requestDto);

    Map<String, Object> getDiagnosisById(Integer id);

    List<Map<String,Object>> listAllByPatientId(Integer id);

    Integer saveDiagnosisTestResultAndPrescription(DiagnosisTestResultPrescriptionRequestDto requestDto);

    List<Map<String, Object>> listAllByDoctorId(Integer id);
}
