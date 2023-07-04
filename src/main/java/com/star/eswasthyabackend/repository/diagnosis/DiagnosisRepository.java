package com.star.eswasthyabackend.repository.diagnosis;

import com.star.eswasthyabackend.model.diagnosis.Diagnosis;
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
            value = "SELECT d.id                                       as \"districtId\",\n" +
                    "       d.name                                     AS \"districtName\",\n" +
                    "       COALESCE(count(diagnosis.disease_name), 0) AS \"count\"\n" +
                    "FROM district d\n" +
                    "         LEFT JOIN location l ON l.district_id = d.id\n" +
                    "         LEFT JOIN patient_details pd ON pd.location_id = l.id\n" +
                    "         LEFT JOIN diagnosis ON diagnosis.patient_detail_id = pd.patient_detail_id\n" +
                    "    AND diagnosis.disease_name ilike concat('%', ?1, '%')\n" +
                    "GROUP BY d.id, d.name\n" +
                    "order by d.id")
    List<Map<String, Object>> getDiseaseCountPerDistrict(String diseaseName);

    @Query(nativeQuery = true, value = "select province_name          as \"provinceName\",\n" +
            "       count(d2.disease_name) as \"count\"\n" +
            "from district d\n" +
            "         left join location l on d.id = l.district_id\n" +
            "         left join patient_details pd on l.id = pd.location_id\n" +
            "         left join diagnosis d2 on pd.patient_detail_id = d2.patient_detail_id and d2.disease_name = ?2\n" +
            "where province_no = ?1\n" +
            "group by province_name")
    Map<String, String> getDiseaseCountInProvince(Integer provinceId, String diseaseName);

    @Query(nativeQuery = true, value = "select d.name                 as \"districtName\",\n" +
            "       count(d2.disease_name) as \"count\"\n" +
            "from district d\n" +
            "         left join location l on d.id = l.district_id\n" +
            "         left join patient_details pd on l.id = pd.location_id\n" +
            "         left join diagnosis d2 on pd.patient_detail_id = d2.patient_detail_id and d2.disease_name = ?2\n" +
            "where d.id = ?1\n" +
            "group by d.name")
    Map<String, Object> totalDiseaseCountInDistrict(Integer districtId, String diseaseName);

    @Query(nativeQuery = true, value = "SELECT m.name                              AS \"municipalityName\",\n" +
            "       COALESCE(COUNT(d2.disease_name), 0) AS \"count\"\n" +
            "FROM municipality m\n" +
            "         LEFT JOIN district d ON d.id = m.district_id\n" +
            "         LEFT JOIN location l ON m.id = l.municipality_id\n" +
            "         LEFT JOIN patient_details pd ON l.id = pd.location_id\n" +
            "         LEFT JOIN diagnosis d2 on pd.patient_detail_id = d2.patient_detail_id and d2.disease_name = ?2\n" +
            "WHERE d.id = ?1\n" +
            "GROUP BY m.name")
    List<Map<String, Object>> totalDiseaseCountPerMunicipality(Integer districtId, String diseaseName);

    @Query(nativeQuery = true, value = "SELECT d.name                                     AS \"districtName\",\n" +
            "       COALESCE(count(diagnosis.disease_type), 0) AS \"count\"\n" +
            "FROM district d\n" +
            "         LEFT JOIN location l ON l.district_id = d.id\n" +
            "         LEFT JOIN patient_details pd ON pd.location_id = l.id\n" +
            "         LEFT JOIN diagnosis ON diagnosis.patient_detail_id = pd.patient_detail_id\n" +
            "    AND diagnosis.disease_type = ?1\n" +
            "GROUP BY d.id, d.name\n" +
            "order by d.id")
    List<Map<String, Object>> getDiseaseCountPerDistrictByDiseaseType(String diseaseType);

    @Query(nativeQuery = true, value = "select province_name          as \"provinceName\",\n" +
            "       count(d2.disease_type) as \"count\"\n" +
            "from district d\n" +
            "         left join location l on d.id = l.district_id\n" +
            "         left join patient_details pd on l.id = pd.location_id\n" +
            "         left join diagnosis d2 on pd.patient_detail_id = d2.patient_detail_id and d2.disease_type = ?2\n" +
            "where province_no = ?1\n" +
            "group by province_name")
    Map<String, Object> getDiseaseCountInProvinceByDiseaseType(Integer provinceId, String diseaseType);

    @Query(nativeQuery = true, value = "select d.name                 as \"districtName\",\n" +
            "       count(d2.disease_type) as \"count\"\n" +
            "from district d\n" +
            "         left join location l on d.id = l.district_id\n" +
            "         left join patient_details pd on l.id = pd.location_id\n" +
            "         left join diagnosis d2 on pd.patient_detail_id = d2.patient_detail_id and d2.disease_type = ?2\n" +
            "where d.id = ?1\n" +
            "group by d.name")
    Map<String, Object> getDiseaseCountInDistrictByType(Integer districtId, String diseaseType);

    @Query(nativeQuery = true, value = "SELECT m.name                              AS \"municipalityName\",\n" +
            "       COALESCE(COUNT(d2.disease_type), 0) AS \"count\"\n" +
            "FROM municipality m\n" +
            "         LEFT JOIN district d ON d.id = m.district_id\n" +
            "         LEFT JOIN location l ON m.id = l.municipality_id\n" +
            "         LEFT JOIN patient_details pd ON l.id = pd.location_id\n" +
            "         LEFT JOIN diagnosis d2 on pd.patient_detail_id = d2.patient_detail_id and d2.disease_type = ?2\n" +
            "WHERE d.id = ?1\n" +
            "GROUP BY m.name")
    List<Map<String, Object>> getDiseaseCountPerMunicipalityByType(Integer districtId, String diseaseType);
}