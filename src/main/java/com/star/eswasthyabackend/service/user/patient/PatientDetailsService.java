package com.star.eswasthyabackend.service.user.patient;

import com.star.eswasthyabackend.dto.user.patient.PatientDetailsRequestDto;

public interface PatientDetailsService {
    Integer savePatientDetails(PatientDetailsRequestDto requestDto);
}
