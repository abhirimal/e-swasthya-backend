package com.star.eswasthyabackend.repository.user;

import com.star.eswasthyabackend.model.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccinationRepository extends JpaRepository<Vaccination, Integer> {

}
