package com.star.eswasthyabackend.repository.location;

import com.star.eswasthyabackend.model.location.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MunicipalityRepository extends JpaRepository<Municipality, Integer> {

    @Query(nativeQuery = true, value = "SELECT id, name\n" +
            "FROM municipality\n" +
            "WHERE district_id = ?1")
    List<Map<String, Object>> listMunicipalityByDistrictId(Integer districtId);

    @Query(nativeQuery = true, value = "SELECT count(*) from municipality")
    Integer countMunicipality();
}
