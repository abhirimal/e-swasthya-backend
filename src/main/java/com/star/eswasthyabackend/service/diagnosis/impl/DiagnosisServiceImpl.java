package com.star.eswasthyabackend.service.diagnosis.impl;

import com.star.eswasthyabackend.dto.diagnosis.DiagnosisRequestDto;
import com.star.eswasthyabackend.exception.AppException;
import com.star.eswasthyabackend.model.Diagnosis;
import com.star.eswasthyabackend.model.doctor.DoctorDetails;
import com.star.eswasthyabackend.model.patient.PatientDetails;
import com.star.eswasthyabackend.repository.DiagnosisRepository;
import com.star.eswasthyabackend.repository.user.doctor.DoctorDetailsRepository;
import com.star.eswasthyabackend.repository.user.patient.PatientDetailsRepository;
import com.star.eswasthyabackend.service.diagnosis.DiagnosisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DiagnosisServiceImpl implements DiagnosisService {

    private final DiagnosisRepository diagnosisRepository;
    private final DoctorDetailsRepository doctorDetailsRepository;
    private final PatientDetailsRepository patientDetailsRepository;
    @Override
    public Integer saveDiagnosis(DiagnosisRequestDto requestDto) {

        Diagnosis diagnosis;
        if(requestDto.getId()!=null){
            diagnosis = diagnosisRepository.findById(requestDto.getId())
                    .orElseThrow(()-> new AppException("Diagnosis not found for given id.", HttpStatus.BAD_REQUEST));
        }
        else {
            diagnosis = new Diagnosis();
        }
        diagnosis.setDiagnosisDescription(requestDto.getDiagnosisDescription());
        diagnosis.setDiseaseName(requestDto.getDiseaseName());

        PatientDetails patientDetail = patientDetailsRepository.findById(requestDto.getPatientDetailId())
                .orElseThrow(()-> new AppException("Patient not found for given id", HttpStatus.BAD_REQUEST));
        DoctorDetails doctorDetail = doctorDetailsRepository.findById(requestDto.getDoctorDetailId())
                .orElseThrow(()-> new AppException("Doctor not found for given id", HttpStatus.BAD_REQUEST));

        diagnosis.setPatientDetail(patientDetail);
        diagnosis.setDoctorDetail(doctorDetail);
        diagnosisRepository.saveAndFlush(diagnosis);

        return diagnosis.getId();
    }

    @Override
    public Map<String, Object> getDiagnosisById(Integer id) {
        return diagnosisRepository.getByDiagnosisId(id);
    }

    @Override
    public List<Map<String, Object>> listAllByPatientId(Integer id) {
        return diagnosisRepository.getAllByPatientId(id);
    }
}
