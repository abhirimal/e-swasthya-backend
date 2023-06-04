package com.star.eswasthyabackend.service.user.vaccination;

import com.star.eswasthyabackend.dto.VaccinationRequest;

import java.util.List;
import java.util.Map;

public interface VaccinationService {
    Integer saveVaccinationReport(VaccinationRequest vaccinationRequest);

    Map<String, Object> viewById(Integer vaccinationId);

    List<Map<String, Object>> viewAllVaccinationReport();

    List<Map<String, Object>> findByPatientId(Integer patientId);
}
