package com.star.eswasthyabackend.service.diagnosis.impl;

import com.star.eswasthyabackend.repository.DiagnosisRepository;
import com.star.eswasthyabackend.service.diagnosis.DiagnosisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiagnosisServiceImpl implements DiagnosisService {

    private final DiagnosisRepository diagnosisRepository;


}
