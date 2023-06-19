package com.star.eswasthyabackend.service.prescription;

import com.star.eswasthyabackend.dto.medication.PrescriptionRequestDto;

public interface PrescriptionService {
    Integer savePrescription(PrescriptionRequestDto requestDto);
}
