package com.star.eswasthyabackend.repository.location;

import com.star.eswasthyabackend.model.location.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {

    @Query(nativeQuery = true, value = "SELECT DISTINCT province_no   AS \"provinceNo\",\n" +
            "                province_name AS \"provinceName\"\n" +
            "FROM district\n" +
            "ORDER BY province_no ASC")
    List<Map<String, Object>> listAllProvince();

    @Query(nativeQuery = true, value = "SELECT id, name\n" +
            "FROM district\n" +
            "WHERE province_no = ?1")
    List<Map<String, Object>> listDistrictByProvinceNo(Integer provinceId);
}
