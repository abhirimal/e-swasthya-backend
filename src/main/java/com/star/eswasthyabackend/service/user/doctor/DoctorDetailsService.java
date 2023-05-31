package com.star.eswasthyabackend.service.user.doctor;

import com.star.eswasthyabackend.dto.user.doctor.DoctorDetailsRequestDto;

public interface DoctorDetailsService {
    Integer saveDoctorDetails(DoctorDetailsRequestDto requestDto);
}
