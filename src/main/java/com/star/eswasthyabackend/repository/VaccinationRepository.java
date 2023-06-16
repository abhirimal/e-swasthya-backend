package com.star.eswasthyabackend.repository;

import com.star.eswasthyabackend.model.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface VaccinationRepository extends JpaRepository<Vaccination, Integer> {


    @Query(value = "select id,\n" +
            "       dosage,\n" +
            "       vaccine_name       as \"vaccineName\",\n" +
            "       vaccination_date   as \"vaccinationDate\",\n" +
            "       patient_detail_id as \"patientId\"\n" +
            "from vaccination\n" +
            "where id = ?1", nativeQuery = true)
    Map<String, Object> findByVaccinationId(Integer vaccinationId);

    @Query(value = "select id,\n" +
            "       dosage,\n" +
            "       vaccine_name       as \"vaccineName\",\n" +
            "       vaccination_date   as \"vaccinationDate\",\n" +
            "       patient_detail_id as \"patientId\"\n" +
            "from vaccination\n", nativeQuery = true)
    List<Map<String, Object>> findAllVaccinationReport();

    @Query(value = "select id,\n" +
            "       dosage,\n" +
            "       vaccine_name       as \"vaccineName\",\n" +
            "       vaccination_date   as \"vaccinationDate\",\n" +
            "       patient_detail_id as \"patientId\"\n" +
            "from vaccination\n" +
            "where patient_detail_id = ?1", nativeQuery = true)
    List<Map<String, Object>> findByPatientId(Integer patientId);
}
