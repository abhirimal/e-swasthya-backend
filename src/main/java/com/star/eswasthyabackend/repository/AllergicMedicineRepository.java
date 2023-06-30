package com.star.eswasthyabackend.repository;
import com.star.eswasthyabackend.model.AllergicMedicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllergicMedicineRepository extends JpaRepository<AllergicMedicine, Integer> {
}
