package com.star.eswasthyabackend.service.prescription.impl;

import com.star.eswasthyabackend.dto.prescription.PrescriptionRequestDto;
import com.star.eswasthyabackend.exception.AppException;
import com.star.eswasthyabackend.model.Prescription;
import com.star.eswasthyabackend.model.diagnosis.Diagnosis;
import com.star.eswasthyabackend.model.doctor.DoctorDetails;
import com.star.eswasthyabackend.model.patient.PatientDetails;
import com.star.eswasthyabackend.repository.diagnosis.DiagnosisRepository;
import com.star.eswasthyabackend.repository.prescription.MedicineRepository;
import com.star.eswasthyabackend.repository.prescription.PrescriptionRepository;
import com.star.eswasthyabackend.repository.user.doctor.DoctorDetailsRepository;
import com.star.eswasthyabackend.repository.user.patient.PatientDetailsRepository;
import com.star.eswasthyabackend.service.prescription.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final PatientDetailsRepository patientDetailsRepository;
    private final DoctorDetailsRepository doctorDetailsRepository;
    private final MedicineRepository medicineRepository;
    private final DiagnosisRepository diagnosisRepository;

    @Override
    public Integer savePrescription(PrescriptionRequestDto requestDto) {

        PatientDetails patientDetails = patientDetailsRepository.findById(requestDto.getPatientDetailId())
                .orElseThrow(()-> new AppException("Patient not found or given id.", HttpStatus.BAD_REQUEST));
        DoctorDetails doctorDetails = doctorDetailsRepository.findById(requestDto.getDoctorDetailId())
                .orElseThrow(()-> new AppException("Doctor not found for given id", HttpStatus.BAD_REQUEST));
        Diagnosis diagnosis = diagnosisRepository.findById(requestDto.getDiagnosisId())
                .orElseThrow(()-> new AppException("Diagnosis not found for given id", HttpStatus.BAD_REQUEST));

        Prescription prescription = new Prescription();

//        if(requestDto.getId()!=null){
//            prescription = prescriptionRepository.findById(requestDto.getId())
//                    .orElseThrow(()-> new AppException("Prescription not found for given id", HttpStatus.BAD_REQUEST));
//        }
//        else{
//            prescription = new Prescription();
//        }
        prescription.setMedicineName(requestDto.getMedicineName());
        prescription.setMedicineType(requestDto.getMedicineType());
        prescription.setDosageInUnit(requestDto.getDosageInUnit());
        prescription.setFrequencyPerDay(requestDto.getFrequencyPerDay());
        prescription.setDurationInDays(requestDto.getDurationInDays());
        prescription.setAdditionalNote(requestDto.getAdditionalNote());
        prescription.setStartDate(requestDto.getStartDate());

        LocalDate endDate = requestDto.getStartDate().plusDays(requestDto.getDurationInDays());
        prescription.setEndDate(endDate);

        prescription.setIsActive(LocalDate.now().isBefore(endDate) && LocalDate.now()
                .isAfter(requestDto.getStartDate()));

        prescription.setPatientDetail(patientDetails);
        prescription.setDoctorDetail(doctorDetails);
        prescription.setDiagnosis(diagnosis);
        prescriptionRepository.saveAndFlush(prescription);

        return prescription.getId();
    }

    @Override
    public Map<String, Object> viewPrescriptionById(Integer id) {
        return prescriptionRepository.viewById(id);
    }

    @Override
    public List<Map<String, String>> listMedicineNames() {
        return medicineRepository.listMedicineNames();
    }

    @Override
    public List<String> listMedicineType() {
        return medicineRepository.listMedicineType();
    }

    @Override
    public List<String> listMedicineNameByMedicineType(String medicineType) {
        return medicineRepository.listMedicineNameByMedicineType(medicineType);
    }

    @Override
    public List<Map<String, String>> listPrescriptionByPatientId(Integer patientId) {
        return prescriptionRepository.listPrescriptionByPatientId(patientId);
    }
}
