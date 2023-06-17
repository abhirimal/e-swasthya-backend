package com.star.eswasthyabackend.service;

import com.star.eswasthyabackend.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService{

    private final HospitalRepository hospitalRepository;
    @Override
    public List<Map<String, Object>> getAllHospitals() {
        return hospitalRepository.listAllHospital();
    }
}
