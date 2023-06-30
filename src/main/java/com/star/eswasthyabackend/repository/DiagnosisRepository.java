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
            "       d.appointment_id                            as \"appointmentId\",\n" +
            "       d.disease_name                              as \"diseaseName\",\n" +
            "       d.diagnosis_description                     as \"diagnosisDescription\",\n" +
            "       d.doctor_detail_id                          as \"doctorDetailId\",\n" +
            "       d.patient_detail_id                         as \"patientDetailId\",\n" +
            "       concat_ws(' ', dd.first_name, dd.last_name) as \"doctorFullName\",\n" +
            "       dd.image_path                               as \"imagePath\",\n" +
            "       dd.education                                as \"education\",\n" +
            "       dd.nmc_license_no                           as \"doctorNmcLicenseNo\",\n" +
            "       dd.specialization                           as \"doctorSpecialization\",\n" +
            "       string_agg(p.medicine_name, ',')            as \"medicineName\",\n" +
            "       h.hospital_name                             as \"hospitalName\",\n" +
            "       d.date                                      as \"date\"\n" +
            "from diagnosis d\n" +
            "         inner join appointment a on a.id = d.appointment_id\n" +
            "         inner join hospital h on h.id = a.hospital_id\n" +
            "         inner join prescription p on d.id = p.diagnosis_id\n" +
            "         inner join doctor_details dd on dd.doctor_detail_id = d.doctor_detail_id\n" +
            "         inner join patient_details pd on pd.patient_detail_id = d.patient_detail_id\n" +
            "where d.patient_detail_id = ?1\n" +
            "group by d.id,\n" +
            "         d.appointment_id,\n" +
            "         d.disease_name,\n" +
            "         d.diagnosis_description,\n" +
            "         d.doctor_detail_id,\n" +
            "         d.patient_detail_id,\n" +
            "         dd.first_name,\n" +
            "         dd.last_name,\n" +
            "         dd.image_path,\n" +
            "         dd.education,\n" +
            "         dd.nmc_license_no,\n" +
            "         dd.specialization,\n" +
            "         h.hospital_name,\n" +
            "         d.date", nativeQuery = true)
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

    @Query(nativeQuery = true, value = "select * from diagnosis d\n" +
            "inner join appointment a on a.id = d.appointment_id\n" +
            "where appointment_id = ?1")
    Optional<Diagnosis> findByAppointmentId(Integer appointmentId);

    @Query(nativeQuery = true,
            value = "SELECT d.id                                as \"districtId\",\n" +
            "       d.name                                     AS \"districtName\",\n" +
            "       COALESCE(count(diagnosis.disease_name), 0) AS \"diseaseCount\"\n" +
            "FROM district d\n" +
            "         LEFT JOIN location l ON l.district_id = d.id\n" +
            "         LEFT JOIN patient_details pd ON pd.location_id = l.id\n" +
            "         LEFT JOIN diagnosis ON diagnosis.patient_detail_id = pd.patient_detail_id AND diagnosis.disease_name = ?1\n" +
            "GROUP BY d.id, d.name\n" +
            "order by d.id")
    List<Map<String, Object>> getDiseaseCountByDistrict(String diseaseName);
}
