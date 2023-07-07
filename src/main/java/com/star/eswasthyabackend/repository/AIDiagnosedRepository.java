package com.star.eswasthyabackend.repository;

import com.star.eswasthyabackend.model.AIDiagnosedDisease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface AIDiagnosedRepository extends JpaRepository<AIDiagnosedDisease, Integer> {
    @Query(nativeQuery = true, value = "select id,\n" +
            "       name,\n" +
            "       image_path  as \"imagePath\",\n" +
            "       is_detected as \"isDetected\"\n" +
            "from ai_diagnosed_disease\n" +
            "where appointment_id = ?1")
    Map<String, Object> findByAppointmentId(Integer appointmentId);
}
