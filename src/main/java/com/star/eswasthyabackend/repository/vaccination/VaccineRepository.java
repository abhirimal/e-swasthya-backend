package com.star.eswasthyabackend.repository.vaccination;

import com.star.eswasthyabackend.model.vaccination.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Integer> {
    @Query(nativeQuery = true, value = "select count(id)\n" +
            "from vaccine")
    Integer countVaccine();

    @Query(nativeQuery = true, value = "select distinct vaccine_name as \"vaccineName\"\n" +
            "from vaccine")
    List<String> listVaccines();
}
