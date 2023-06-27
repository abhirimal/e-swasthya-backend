package com.star.eswasthyabackend.service.diagnosis.impl;

import com.star.eswasthyabackend.dto.diagnosis.DiagnosisRequestDto;
import com.star.eswasthyabackend.dto.diagnosis.DiagnosisTestResultPrescriptionRequestDto;
import com.star.eswasthyabackend.exception.AppException;
import com.star.eswasthyabackend.model.Appointment;
import com.star.eswasthyabackend.model.Diagnosis;
import com.star.eswasthyabackend.model.Prescription;
import com.star.eswasthyabackend.model.TestResult;
import com.star.eswasthyabackend.model.doctor.DoctorDetails;
import com.star.eswasthyabackend.model.patient.PatientDetails;
import com.star.eswasthyabackend.repository.AppointmentRepository;
import com.star.eswasthyabackend.repository.DiagnosisRepository;
import com.star.eswasthyabackend.repository.PrescriptionRepository;
import com.star.eswasthyabackend.repository.TestResultRepository;
import com.star.eswasthyabackend.repository.user.doctor.DoctorDetailsRepository;
import com.star.eswasthyabackend.repository.user.patient.PatientDetailsRepository;
import com.star.eswasthyabackend.service.diagnosis.DiagnosisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiagnosisServiceImpl implements DiagnosisService {

    private final DiagnosisRepository diagnosisRepository;
    private final DoctorDetailsRepository doctorDetailsRepository;
    private final PatientDetailsRepository patientDetailsRepository;
    private final TestResultRepository testResultRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final AppointmentRepository appointmentRepository;
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

    @Override
    public Integer saveDiagnosisTestResultAndPrescription(DiagnosisTestResultPrescriptionRequestDto requestDto) {

        PatientDetails patientDetail = patientDetailsRepository.findById(requestDto.getDiagnosis().getPatientDetailId())
                .orElseThrow(() -> new AppException("Patient doesn't exist for given id", HttpStatus.BAD_REQUEST));
        DoctorDetails doctorDetail = doctorDetailsRepository.findById(requestDto.getDiagnosis().getDoctorDetailId())
                .orElseThrow(() -> new AppException("Doctor not found for given id", HttpStatus.BAD_REQUEST));
        Appointment appointment = appointmentRepository.findById(requestDto.getDiagnosis().getAppointmentId())
                .orElseThrow(() -> new AppException("Appointment not found for given id", HttpStatus.BAD_REQUEST));

        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setDiseaseName(requestDto.getDiagnosis().getDiseaseName());
        diagnosis.setDiagnosisDescription(requestDto.getDiagnosis().getDiagnosisDescription());
        diagnosis.setPatientDetail(patientDetail);
        diagnosis.setDoctorDetail(doctorDetail);
        diagnosis.setAppointment(appointment);
        appointment.setIsDiagnosisFilled(true);
        diagnosisRepository.saveAndFlush(diagnosis);

        //save test result
        if (requestDto.getTestResultList() != null) {
            List<TestResult> testResultList;

            testResultList = requestDto.getTestResultList().stream().map(
                    newTestResult -> new TestResult(newTestResult, diagnosis, doctorDetail, patientDetail)
            ).collect(Collectors.toList());
            testResultRepository.saveAll(testResultList);
        }

        //save prescription
        if(requestDto.getPrescriptionList()!=null){
            List<Prescription> prescriptionList;

            prescriptionList = requestDto.getPrescriptionList().stream().map(
                    newPrescription -> new Prescription(newPrescription, diagnosis, doctorDetail, patientDetail)
            ).collect(Collectors.toList());
            prescriptionRepository.saveAll(prescriptionList);
        }

        return diagnosis.getId();
    }

    @Override
    public List<Map<String, Object>> listAllByDoctorId(Integer id) {
        return diagnosisRepository.listAllByDoctorId(id);
    }
}
