package com.star.eswasthyabackend.repository;
import com.star.eswasthyabackend.model.AllergicMedicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AllergicMedicineRepository extends JpaRepository<AllergicMedicine, Integer> {
    @Query(nativeQuery = true, value = "select id                     as \"id\",\n" +
            "       allergic_medicine_name as \"allergicMedicineName\"\n" +
            "from allergic_medicine\n" +
            "where patient_detail_id = ?1")
    List<Map<String, String>> listAllergicMedicineByPatientId(Integer patientId);
}
