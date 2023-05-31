package com.star.eswasthyabackend.service.user.doctor;

import com.star.eswasthyabackend.dto.user.doctor.DoctorDetailsRequestDto;

import java.util.List;
import java.util.Map;

public interface DoctorDetailsService {
    Integer saveDoctorDetails(DoctorDetailsRequestDto requestDto);

    Map<String, Object> findById(Integer id);

    List<Map<String, Object>> listAllDoctor();
}
