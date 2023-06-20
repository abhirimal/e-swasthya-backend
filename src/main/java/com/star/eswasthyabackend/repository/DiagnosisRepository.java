package com.star.eswasthyabackend.repository;

import com.star.eswasthyabackend.model.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis, Integer> {

    @Query(value = "select id             as \"id\",\n" +
            "       disease_name          as \"diseaseName\",\n" +
            "       diagnosis_description as \"diagnosisDescription\",\n" +
            "       doctor_detail_id      as \"doctorDetailId\",\n" +
            "       patient_detail_id     as patientDetailId\n" +
            "from diagnosis\n" +
            "where id = ?1", nativeQuery = true)
    Map<String, Object> getByDiagnosisId(Integer id);
}
