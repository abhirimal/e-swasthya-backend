package com.star.eswasthyabackend.repository.location;

import com.star.eswasthyabackend.model.location.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MunicipalityRepository extends JpaRepository<Municipality, Integer> {
}
