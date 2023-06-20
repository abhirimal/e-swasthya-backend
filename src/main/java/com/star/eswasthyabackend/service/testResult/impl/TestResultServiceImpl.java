package com.star.eswasthyabackend.service.testResult.impl;

import com.star.eswasthyabackend.dto.testResult.TestResultRequestDto;
import com.star.eswasthyabackend.dto.testResult.TestResultResponseDto;
import com.star.eswasthyabackend.exception.AppException;
import com.star.eswasthyabackend.model.TestResult;
import com.star.eswasthyabackend.model.doctor.DoctorDetails;
import com.star.eswasthyabackend.model.patient.PatientDetails;
import com.star.eswasthyabackend.repository.TestResultRepository;
import com.star.eswasthyabackend.repository.user.doctor.DoctorDetailsRepository;
import com.star.eswasthyabackend.repository.user.patient.PatientDetailsRepository;
import com.star.eswasthyabackend.service.testResult.TestResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TestResultServiceImpl implements TestResultService {

    private final TestResultRepository testResultRepository;
    private final PatientDetailsRepository patientDetailsRepository;
    private final DoctorDetailsRepository doctorDetailsRepository;
    @Override
    public Integer saveTestResult(TestResultRequestDto requestDto) {

        TestResult testResult;
        if(requestDto.getId()!=null){
            testResult = testResultRepository.findById(requestDto.getId())
                    .orElseThrow(()-> new AppException("Test result not found", HttpStatus.BAD_REQUEST));
        }
        else {
            testResult = new TestResult();
        }
        testResult.setTestName(requestDto.getTestName());
        testResult.setTestType(requestDto.getTestType());
        testResult.setResult(requestDto.getResult());
        testResult.setDescription(requestDto.getDescription());
        testResult.setTestDate(requestDto.getTestDate());
        testResult.setImagePath(requestDto.getImagePath());

        PatientDetails patientDetail = patientDetailsRepository.findById(requestDto.getPatientDetailId())
                .orElseThrow(()-> new AppException("Patient not found for given id", HttpStatus.BAD_REQUEST));
        DoctorDetails doctorDetails = doctorDetailsRepository.findById(requestDto.getRecommendedDoctorDetailId())
                .orElseThrow(()-> new AppException("Doctor not found for given id", HttpStatus.BAD_REQUEST));

        testResult.setPatientDetail(patientDetail);
        testResult.setRecommendedDoctorDetail(doctorDetails);
        testResultRepository.saveAndFlush(testResult);
        return testResult.getId();
    }

    @Override
    public TestResultResponseDto findById(Integer testResultId) {

        TestResult testResult = testResultRepository.findById(testResultId)
                .orElseThrow(()-> new AppException("Test not found for given id.", HttpStatus.BAD_REQUEST));
        return new TestResultResponseDto(testResult);
    }

    @Override
    public List<Map<String, Object>> findAllByPatientId(Integer patientId) {

        return testResultRepository.findAllByPatientId(patientId);
    }

}
