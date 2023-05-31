package com.star.eswasthyabackend.repository.user.doctor;

import com.star.eswasthyabackend.model.user.doctor.DoctorDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorDetailsRepository extends JpaRepository<DoctorDetails, Integer> {
}
