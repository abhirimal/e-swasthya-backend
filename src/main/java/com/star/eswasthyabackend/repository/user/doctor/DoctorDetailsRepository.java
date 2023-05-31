package com.star.eswasthyabackend.repository.user.doctor;

import com.star.eswasthyabackend.model.user.doctor.DoctorDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface DoctorDetailsRepository extends JpaRepository<DoctorDetails, Integer> {

    @Query(value = "select doctor_detail_id    as \"doctorDetailId\",\n" +
            "       first_name          as \"firstName\",\n" +
            "       last_name           as \"lastName\",\n" +
            "       email,\n" +
            "       nmc_license_no      as \"nmcLicenseNumber\",\n" +
            "       phone_number        as \"phoneNumber\",\n" +
            "       experience,\n" +
            "       specialization,\n" +
            "       education,\n" +
            "       associated_hospital as \"associatedHospital\",\n" +
            "       gender,\n" +
            "       location\n" +
            "from doctor_details\n" +
            "where doctor_detail_id = ?1", nativeQuery = true)
    Map<String, Object> findDoctorDetailsById(Integer id);

    @Query(value = "select doctor_detail_id    as \"doctorDetailId\",\n" +
            "       first_name          as \"firstName\",\n" +
            "       last_name           as \"lastName\",\n" +
            "       email,\n" +
            "       nmc_license_no      as \"nmcLicenseNumber\",\n" +
            "       phone_number        as \"phoneNumber\",\n" +
            "       experience,\n" +
            "       specialization,\n" +
            "       education,\n" +
            "       associated_hospital as \"associatedHospital\",\n" +
            "       gender,\n" +
            "       location\n" +
            "from doctor_details", nativeQuery = true)
    List<Map<String, Object>> listAllDoctor();
}
