package com.star.eswasthyabackend.service.vaccination;

import com.star.eswasthyabackend.dto.vaccination.VaccinationRequest;
import com.star.eswasthyabackend.exception.AppException;
import com.star.eswasthyabackend.model.patient.PatientDetails;
import com.star.eswasthyabackend.model.vaccination.Vaccination;
import com.star.eswasthyabackend.repository.vaccination.VaccinationRepository;
import com.star.eswasthyabackend.repository.user.patient.PatientDetailsRepository;
import com.star.eswasthyabackend.repository.vaccination.VaccineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VaccinationServiceImpl implements VaccinationService {

    private final VaccinationRepository vaccinationRepository;
    private final PatientDetailsRepository patientDetailsRepository;
    private final VaccineRepository vaccineRepository;

    @Override
    public Integer saveVaccinationReport(VaccinationRequest vaccinationRequest) {

        Vaccination vaccination;

        if (vaccinationRequest.getId() != null) {
            vaccination = vaccinationRepository.findById(vaccinationRequest.getId())
                    .orElseThrow(() -> new AppException("Vaccination Report not found", HttpStatus.BAD_REQUEST));
        } else {
            vaccination = new Vaccination();
        }

        PatientDetails patientDetails = patientDetailsRepository.findById(vaccinationRequest.getPatientId())
                .orElseThrow(() -> new AppException("Patient not found for given id.", HttpStatus.BAD_REQUEST));

        vaccination.setVaccineName(vaccinationRequest.getVaccineName());
        vaccination.setVaccinationDate(vaccinationRequest.getVaccinationDate());
        vaccination.setDosage(vaccinationRequest.getDosage());
        vaccination.setPatientDetails(patientDetails);
        vaccinationRepository.saveAndFlush(vaccination);
        return vaccination.getId();
    }

    @Override
    public Map<String, Object> viewById(Integer vaccinationId) {
        return vaccinationRepository.findByVaccinationId(vaccinationId);
    }

    @Override
    public List<Map<String, Object>> viewAllVaccinationReport() {
        return vaccinationRepository.findAllVaccinationReport();
    }

    @Override
    public List<Map<String, Object>> findByPatientId(Integer patientId) {
        return vaccinationRepository.findByPatientId(patientId);
    }

    @Override
    public List<String> listVaccines() {
        return vaccineRepository.listVaccines();
    }
}