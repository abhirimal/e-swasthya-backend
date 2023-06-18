package com.star.eswasthyabackend.service.user.doctor;

import com.star.eswasthyabackend.dto.user.doctor.DoctorDetailResponseDto;
import com.star.eswasthyabackend.dto.user.doctor.DoctorDetailsRequestDto;

import java.util.List;
import java.util.Map;

public interface DoctorDetailsService {
    String saveDoctorDetails(DoctorDetailsRequestDto requestDto);

    DoctorDetailResponseDto findById(Integer id);

    List<Map<String, Object>> listAllDoctor();
}
