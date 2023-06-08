package com.star.eswasthyabackend.repository.user.patient;

import com.star.eswasthyabackend.model.patient.PatientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PatientDetailsRepository extends JpaRepository<PatientDetails, Integer> {

    @Query(value = "Select patient_id as \"patientId\",\n" +
            "       medical_record_number as \"medicalRecordNumber\",\n" +
            "       citizenship_no as \"citizenshipNo\",\n" +
            "       first_name as \"firstName\",\n" +
            "       last_name as \"lastName\",\n" +
            "       email,\n" +
            "       phone_number as \"phoneNumber\",\n" +
            "       blood_group as \"bloodGroup\",\n" +
            "       date_of_birth as \"dateOfBirth\",\n" +
            "       weight\n" +
            "from patient_details where patient_id = ?1", nativeQuery = true)
    Map<String, String> findPatientDetail(Integer id);

    @Query(value = "Select patient_detail_id     as \"patientId\",\n" +
            "       medical_record_number as \"medicalRecordNumber\",\n" +
            "       citizenship_no        as \"citizenshipNo\",\n" +
            "       first_name            as \"firstName\",\n" +
            "       last_name             as \"lastName\",\n" +
            "       email,\n" +
            "       phone_number          as \"phoneNumber\",\n" +
            "       blood_group           as \"bloodGroup\",\n" +
            "       date_of_birth         as \"dateOfBirth\",\n" +
            "       weight,\n" +
            "       height,\n" +
            "       image_path            as \"imagePath\"\n" +
            "from patient_details", nativeQuery = true)
    List<Map<String, String>> findAllPatientDetail();
}
