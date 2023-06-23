package com.star.eswasthyabackend.repository.user.doctor;

import com.star.eswasthyabackend.model.doctor.DoctorDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface DoctorDetailsRepository extends JpaRepository<DoctorDetails, Integer> {

    @Query(value = "select doctor_detail_id as \"doctorId\",\n" +
            "       first_name       as \"firstName\",\n" +
            "       last_name        as \"lastName\",\n" +
            "       email,\n" +
            "       nmc_license_no   as \"nmcLicenseNumber\",\n" +
            "       phone_number     as \"phoneNumber\",\n" +
            "       experience,\n" +
            "       specialization,\n" +
            "       education,\n" +
            "       gender,\n" +
            "       image_path as \"imagePath\",\n" +
            "       string_agg(h.hospital_name, ',') as \"hospitalNames\"\n" +
            "from doctor_details\n" +
            "         inner join doctor_hospital dh on doctor_details.doctor_detail_id = dh.doctor_id\n" +
            "         inner join hospital h on h.id = dh.hospital_id\n" +
            "where doctor_detail_id = ?1\n" +
            "group by doctor_detail_id", nativeQuery = true)
    Map<String, Object> findDoctorDetailsById(Integer id);

    @Query(value = "select doctor_detail_id as \"doctorId\",\n" +
            "       first_name       as \"firstName\",\n" +
            "       last_name        as \"lastName\",\n" +
            "       email,\n" +
            "       nmc_license_no   as \"nmcLicenseNumber\",\n" +
            "       phone_number     as \"phoneNumber\",\n" +
            "       experience,\n" +
            "       specialization,\n" +
            "       education,\n" +
            "       gender,\n" +
            "       image_path as \"imagePath\",\n" +
            "       string_agg(h.hospital_name, ',') as \"hospitalNames\"\n" +
            "from doctor_details\n" +
            "         inner join doctor_hospital dh on doctor_details.doctor_detail_id = dh.doctor_id\n" +
            "         inner join hospital h on h.id = dh.hospital_id\n" +
            "group by doctor_detail_id", nativeQuery = true)
    List<Map<String, Object>> listAllDoctor();

    @Query(nativeQuery = true, value = "SELECT COUNT(*) AS row_count\n" +
            "FROM doctor_details\n" +
            "where user_id = ?1")
    Integer checkIfDataExists(Integer id);

    @Query(nativeQuery = true, value = "select count(*) AS totalPatient\n" +
            "FROM doctor_details")
    Integer countTotalDoctors();

    @Query(nativeQuery = true, value = "select doctor_detail_id                 as \"doctorId\",\n" +
            "       first_name                       as \"firstName\",\n" +
            "       last_name                        as \"lastName\",\n" +
            "       email,\n" +
            "       nmc_license_no                   as \"nmcLicenseNumber\",\n" +
            "       phone_number                     as \"phoneNumber\",\n" +
            "       experience,\n" +
            "       specialization,\n" +
            "       education,\n" +
            "       gender,\n" +
            "       image_path                       as \"imagePath\",\n" +
            "       string_agg(h.hospital_name, ',') as \"hospitalNames\"\n" +
            "from doctor_details\n" +
            "         inner join doctor_hospital dh on doctor_details.doctor_detail_id = dh.doctor_id\n" +
            "         inner join hospital h on h.id = dh.hospital_id\n" +
            "where user_id = ?1\n" +
            "group by doctor_detail_id")
    Map<String, Object> findDoctorByUserId(Integer id);
}
