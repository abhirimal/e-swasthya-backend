package com.star.eswasthyabackend.repository.location;

import com.star.eswasthyabackend.model.location.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {

}
