package com.star.eswasthyabackend.repository;

import com.star.eswasthyabackend.model.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis, Integer> {

    @Query(value = "select d.id                    as id,\n" +
            "       d.disease_name          as \"diseaseName\",\n" +
            "       d.diagnosis_description as \"diagnosisDescription\",\n" +
            "       d.doctor_detail_id      as \"doctorDetailId\",\n" +
            "       d.patient_detail_id     as \"patientDetailId\",\n" +
            "       concat_ws(' ', dd.first_name, dd.last_name) as \"doctorFullName\",\n" +
            "       dd.nmc_license_no       as \"doctorNmcLicenseNo\",\n" +
            "       dd.specialization       as \"doctorSpecialization\"\n" +
            "from diagnosis d\n" +
            "         inner join doctor_details dd on dd.doctor_detail_id = d.doctor_detail_id\n" +
            "where id = ?1", nativeQuery = true)
    Map<String, Object> getByDiagnosisId(Integer id);

    @Query(value = "select d.id                                        as id,\n" +
            "       d.disease_name                              as \"diseaseName\",\n" +
            "       d.diagnosis_description                     as \"diagnosisDescription\",\n" +
            "       d.doctor_detail_id                          as \"doctorDetailId\",\n" +
            "       d.patient_detail_id                         as \"patientDetailId\",\n" +
            "       concat_ws(' ', dd.first_name, dd.last_name) as \"doctorFullName\",\n" +
            "       dd.nmc_license_no                           as \"doctorNmcLicenseNo\",\n" +
            "       dd.specialization                           as \"doctorSpecialization\"\n" +
            "from diagnosis d\n" +
            "         inner join doctor_details dd on dd.doctor_detail_id = d.doctor_detail_id\n" +
            "         inner join patient_details pd on pd.patient_detail_id = d.patient_detail_id\n" +
            "where d.patient_detail_id = ?1", nativeQuery = true)
    List<Map<String, Object>> getAllByPatientId(Integer id);

    @Query(nativeQuery = true, value = "select d.id                                        as id,\n" +
            "       d.disease_name                              as \"diseaseName\",\n" +
            "       d.diagnosis_description                     as \"diagnosisDescription\",\n" +
            "       d.doctor_detail_id                          as \"doctorDetailId\",\n" +
            "       d.patient_detail_id                         as \"patientDetailId\",\n" +
            "       concat_ws(' ', pd.first_name, dd.last_name) as \"patientFullName\",\n" +
            "       concat_ws(' ', dd.first_name, dd.last_name) as \"doctorFullName\",\n" +
            "       dd.nmc_license_no                           as \"doctorNmcLicenseNo\",\n" +
            "       dd.specialization                           as \"doctorSpecialization\"\n" +
            "from diagnosis d\n" +
            "         inner join patient_details pd on pd.patient_detail_id = d.patient_detail_id\n" +
            "         inner join doctor_details dd on dd.doctor_detail_id = d.doctor_detail_id\n" +
            "where dd.doctor_detail_id = ?1")
    List<Map<String, Object>> listAllByDoctorId(Integer id);

    @Query(nativeQuery = true, value = "select * from appointment a\n" +
            "inner join diagnosis d on a.id = d.appointment_id\n" +
            "where appointment_id = ?1")
    Optional<Diagnosis> findByAppointmentId(Integer appointmentId);
}
