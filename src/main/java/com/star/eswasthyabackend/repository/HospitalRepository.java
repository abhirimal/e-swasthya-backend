package com.star.eswasthyabackend.repository;

import com.star.eswasthyabackend.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Integer> {

    @Query(nativeQuery = true, value = "select count(*) from hospital")
    Integer countHospital();

    @Query(nativeQuery = true, value = "select id            as \"id\",\n" +
            "       hospital_name as \"hospitalName\",\n" +
            "       location_id   as \"locationId\"\n" +
            "from hospital")
    List<Map<String, Object>> listAllHospital();
}
