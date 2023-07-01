package com.star.eswasthyabackend.repository.prescription;

import com.star.eswasthyabackend.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Integer> {

    @Query(nativeQuery = true, value = "select id            as \"id\",\n" +
            "       medicine_name as \"medicineName\",\n" +
            "       medicine_type as \"medicineType\"\n" +
            "from medicine")
    List<Map<String, String>> listMedicineNames();

    @Query(nativeQuery = true, value = "select count(id)\n" +
            "from medicine")
    Integer countMedicine();

    @Query(nativeQuery = true, value = "select distinct medicine_type as \"medicineTpe\"\n" +
            "from medicine")
    List<String> listMedicineType();

    @Query(nativeQuery = true, value = "select medicine_name as \"medicineName\"\n" +
            "from medicine\n" +
            "where medicine_type = ?1")
    List<String> listMedicineNameByMedicineType(String medicineType);
}