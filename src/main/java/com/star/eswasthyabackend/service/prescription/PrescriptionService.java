package com.star.eswasthyabackend.service.prescription;

import com.star.eswasthyabackend.dto.prescription.PrescriptionRequestDto;

import java.util.List;
import java.util.Map;

public interface PrescriptionService {
    Integer savePrescription(PrescriptionRequestDto requestDto);

    Map<String, Object> viewPrescriptionById(Integer id);

    List<Map<String, String>> listMedicineNames();

    List<String> listMedicineType();

    List<String> listMedicineNameByMedicineType(String medicineType);

    List<Map<String, String>> listPrescriptionByPatientId(Integer patientId);
}
