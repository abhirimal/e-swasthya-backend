package com.star.eswasthyabackend.service.prescription;

import com.star.eswasthyabackend.dto.prescription.PrescriptionRequestDto;

import java.util.Map;

public interface PrescriptionService {
    Integer savePrescription(PrescriptionRequestDto requestDto);

    Map<String, Object> viewPrescriptionById(Integer id);
}
