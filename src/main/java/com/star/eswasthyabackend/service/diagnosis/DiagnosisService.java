package com.star.eswasthyabackend.service.diagnosis;

import com.star.eswasthyabackend.dto.diagnosis.DiagnosisRequestDto;
import com.star.eswasthyabackend.dto.diagnosis.DiagnosisResponseDto;
import com.star.eswasthyabackend.dto.diagnosis.DiagnosisTestResultPrescriptionRequestDto;
import com.star.eswasthyabackend.dto.diagnosis.DiagnosisTestResultPrescriptionResponseDto;
import com.star.eswasthyabackend.model.Diagnosis;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DiagnosisService {
    Integer saveDiagnosis(DiagnosisRequestDto requestDto);

    Map<String, Object> getDiagnosisById(Integer id);

    List<Map<String,Object>> listAllByPatientId(Integer id);

    Integer saveDiagnosisTestResultAndPrescription(DiagnosisTestResultPrescriptionRequestDto requestDto);

    List<Map<String, Object>> listAllByDoctorId(Integer id);

    DiagnosisResponseDto viewDiagnosisByAppointmentId(Integer appointmentId);
}
