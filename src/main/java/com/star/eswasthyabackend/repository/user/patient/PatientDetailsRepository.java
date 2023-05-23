package com.star.eswasthyabackend.repository.user.patient;

import com.star.eswasthyabackend.model.user.patient.PatientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientDetailsRepository extends JpaRepository<PatientDetails, Integer> {
}
