package com.star.eswasthyabackend.service.aiDiagnosedDisease.impl;

import com.star.eswasthyabackend.dto.AIDiagnosedDiseaseRequestDto;
import com.star.eswasthyabackend.exception.AppException;
import com.star.eswasthyabackend.model.AIDiagnosedDisease;
import com.star.eswasthyabackend.model.Appointment;
import com.star.eswasthyabackend.repository.AIDiagnosedRepository;
import com.star.eswasthyabackend.repository.AppointmentRepository;
import com.star.eswasthyabackend.service.aiDiagnosedDisease.AIDiagnosedDiseaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AIDiagnosedDiseaseImpl implements AIDiagnosedDiseaseService {

    private final AIDiagnosedRepository aiDiagnosedRepository;
    private final AppointmentRepository appointmentRepository;

    @Override
    public Integer saveAIDiagnosedDisease(AIDiagnosedDiseaseRequestDto requestDto) {

        Appointment appointment = appointmentRepository.findById(requestDto.getAppointmentId())
                .orElseThrow(()-> new AppException("Appointment not found for given id", HttpStatus.BAD_REQUEST));
        //check if ai is already associated with appointment
        Integer countData = aiDiagnosedRepository.countData(requestDto.getAppointmentId());
        if(countData > 0){
            throw new AppException("There is already AI Diagnosed data associated with given appointment id", HttpStatus.BAD_REQUEST);
        }

        AIDiagnosedDisease aiDiagnosedDisease = new AIDiagnosedDisease();
        aiDiagnosedDisease.setName("Pneumonia");
        aiDiagnosedDisease.setImagePath(requestDto.getImagePath());
        aiDiagnosedDisease.setIsDetected(requestDto.getIsDetected());
        aiDiagnosedDisease.setAppointment(appointment);
        aiDiagnosedRepository.saveAndFlush(aiDiagnosedDisease);
        return aiDiagnosedDisease.getId();
    }

    @Override
    public Map<String, Object> getAIDiagnosedDiseaseByAppointmentId(Integer appointmentId) {
        return aiDiagnosedRepository.findByAppointmentId(appointmentId);
    }
}
