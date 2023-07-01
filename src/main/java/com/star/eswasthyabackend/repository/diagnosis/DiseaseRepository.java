package com.star.eswasthyabackend.repository.diagnosis;

import com.star.eswasthyabackend.model.diagnosis.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Integer> {
    @Query(nativeQuery = true, value = "select id           as id,\n" +
            "       disease_name as \"diseaseName\",\n" +
            "       disease_type as \"diseasetype\"\n" +
            "from disease")
    List<Map<String, String>> listAllDiseaseAndType();

    @Query(nativeQuery = true, value = "select count(id)\n" +
            "from disease")
    Integer countDisease();

    @Query(nativeQuery = true,
    value = "select distinct disease_type\n" +
            "from disease")
    List<String> listDiseaseType();

    @Query(nativeQuery = true,
    value = "select disease_name\n" +
            "from disease\n" +
            "where disease_type = ?1")
    List<String> listDiseaseNameByType(String diseaseType);
}
