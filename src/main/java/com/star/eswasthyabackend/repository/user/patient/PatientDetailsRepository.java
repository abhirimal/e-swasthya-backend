package com.star.eswasthyabackend.repository.user.patient;

import com.star.eswasthyabackend.model.patient.PatientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PatientDetailsRepository extends JpaRepository<PatientDetails, Integer> {

    @Query(value = "select distinct patient_detail_id     as \"patientId\",\n" +
            "                medical_record_number as \"medicalRecordNumber\",\n" +
            "                citizenship_no        as \"citizenshipNo\",\n" +
            "                first_name            as \"firstName\",\n" +
            "                last_name             as \"lastName\",\n" +
            "                email                 as \"email\",\n" +
            "                phone_number          as \"phoneNumber\",\n" +
            "                blood_group           as \"bloodGroup\",\n" +
            "                date_of_birth         as \"dateOfBirth\",\n" +
            "                age                   as \"age\",\n" +
            "                height                as \"height\",\n" +
            "                gender                as \"gender\",\n" +
            "                weight                as \"weight\",\n" +
            "                m.name                as \"municipalityName\",\n" +
            "                d.name                as \"districtName\",\n" +
            "                d.province_name       as \"provinceName\",\n" +
            "                l.street_address      as \"address\"\n" +
            "from patient_details pd\n" +
            "         inner join location l on pd.location_id = l.id\n" +
            "         inner join district d on d.id = l.district_id\n" +
            "         inner join municipality m on l.municipality_id = m.id\n" +
            "where patient_detail_id = ?1", nativeQuery = true)
    Map<String, String> findPatientDetail(Integer id);

    @Query(value = " select patient_detail_id     as \"patientId\",\n" +
            "                medical_record_number as \"medicalRecordNumber\",\n" +
            "                citizenship_no        as \"citizenshipNo\",\n" +
            "                first_name            as \"firstName\",\n" +
            "                last_name             as \"lastName\",\n" +
            "                email                 as \"email\",\n" +
            "                phone_number          as \"phoneNumber\",\n" +
            "                blood_group           as \"bloodGroup\",\n" +
            "                date_of_birth         as \"dateOfBirth\",\n" +
            "                age                   as \"age\",\n" +
            "                height                as \"height\",\n" +
            "                gender                as \"gender\",\n" +
            "                weight                as \"weight\",\n" +
            "                m.name                as \"municipalityName\",\n" +
            "                d.name                as \"districtName\",\n" +
            "                d.province_name       as \"provinceName\",\n" +
            "                l.street_address      as \"address\"\n" +
            "from patient_details pd\n" +
            "         inner join location l on pd.location_id = l.id\n" +
            "         inner join district d on d.id = l.district_id\n" +
            "         inner join municipality m on l.municipality_id = m.id\n" , nativeQuery = true)
    List<Map<String, String>> listAllPatient();

    @Query(nativeQuery = true, value = "SELECT COUNT(*) AS row_count\n" +
            "FROM patient_details\n" +
            "where user_id = ?1")
    Integer checkIfDataExists(Integer id);

    @Query(nativeQuery = true, value = "select count(*) AS totalPatient\n" +
            "FROM patient_details")
    Integer countTotalPatients();
}
