package com.star.eswasthyabackend.repository;

import com.star.eswasthyabackend.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    @Query(nativeQuery = true, value = "select ap.id                                       as \"appointmentId\",\n" +
            "       concat_ws(' ', dd.first_name, dd.last_name) as \"doctorName\",\n" +
            "       dd.doctor_detail_id                         as \"doctorId\",\n" +
            "       dd.nmc_license_no                           as \"nmcLicenseNo\",\n" +
            "       concat_ws(' ', pd.first_name, pd.last_name) as \"patientName\",\n" +
            "       pd.patient_detail_id                        as \"patientId\",\n" +
            "       pd.medical_record_number                    as \"medicalRecordNumber\",\n" +
            "       ap.appointment_date                         as \"appointmentDate\",\n" +
            "       ap.appointment_time                         as \"appointmentTime\",\n" +
            "       h.hospital_name                             as \"hospitalName\",\n" +
            "       ap.reason_for_visit                         as \"reasonForVist\",\n" +
            "       ap.status                                   as \"status\",\n" +
            "       ap.is_diagnosis_filled                      as \"isDiagnosisFilled\"\n" +
            "from appointment ap\n" +
            "         inner join doctor_details dd on dd.doctor_detail_id = ap.doctor_detail_id\n" +
            "         inner join patient_details pd on pd.patient_detail_id = ap.patient_detail_id\n" +
            "         inner join hospital h on h.id = ap.hospital_id\n" +
            "where status != 'DELETED'\n" +
            "  and ap.id = ?1\n" +
            "order by ap.appointment_date desc")
    Map<String, Object> viewById(Integer appointmentId);

    @Query(nativeQuery = true, value = "select ap.id                                       as \"appointmentId\",\n" +
            "       concat_ws(' ', dd.first_name, dd.last_name) as \"doctorName\",\n" +
            "       dd.doctor_detail_id                         as \"doctorId\",\n" +
            "       dd.nmc_license_no                           as \"nmcLicenseNo\",\n" +
            "       concat_ws(' ', pd.first_name, pd.last_name) as \"patientName\",\n" +
            "       pd.patient_detail_id                        as \"patientId\",\n" +
            "       pd.medical_record_number                    as \"medicalRecordNumber\",\n" +
            "       ap.appointment_date                         as \"appointmentDate\",\n" +
            "       ap.appointment_time                         as \"appointmentTime\",\n" +
            "       h.hospital_name                             as \"hospitalName\",\n" +
            "       ap.reason_for_visit                         as \"reasonForVist\", \n" +
            "       ap.status                                   as \"status\",\n" +
            "       ap.is_diagnosis_filled                      as \"isDiagnosisFilled\"\n" +
            "from appointment ap\n" +
            "         inner join doctor_details dd on dd.doctor_detail_id = ap.doctor_detail_id\n" +
            "         inner join patient_details pd on pd.patient_detail_id = ap.patient_detail_id\n" +
            "         inner join hospital h on h.id = ap.hospital_id\n" +
            "where ap.doctor_detail_id = ?1\n" +
            "  and case\n" +
            "          when ?2 = 'ALL' then status != 'DELETED'\n" +
            "          else status = ?2 end\n" +
            "order by ap.appointment_date desc\n")
    List<Map<String, Object>> viewByDoctorIdAndStatus(Integer doctorId, String status);

    @Query(nativeQuery = true, value = "select ap.id                                       as \"appointmentId\",\n" +
            "       concat_ws(' ', dd.first_name, dd.last_name) as \"doctorName\",\n" +
            "       dd.doctor_detail_id                         as \"doctorId\",\n" +
            "       dd.nmc_license_no                           as \"nmcLicenseNo\",\n" +
            "       concat_ws(' ', pd.first_name, pd.last_name) as \"patientName\",\n" +
            "       pd.patient_detail_id                        as \"patientId\",\n" +
            "       pd.medical_record_number                    as \"medicalRecordNumber\",\n" +
            "       ap.appointment_date                         as \"appointmentDate\",\n" +
            "       ap.appointment_time                         as \"appointmentTime\",\n" +
            "       h.hospital_name                             as \"hospitalName\",\n" +
            "       ap.reason_for_visit                         as \"reasonForVist\", \n" +
            "       ap.status                                   as \"status\",\n" +
            "       ap.is_diagnosis_filled                      as \"isDiagnosisFilled\"\n" +
            "from appointment ap\n" +
            "         inner join doctor_details dd on dd.doctor_detail_id = ap.doctor_detail_id\n" +
            "         inner join patient_details pd on pd.patient_detail_id = ap.patient_detail_id\n" +
            "         inner join hospital h on h.id = ap.hospital_id\n" +
            "where ap.patient_detail_id = ?1\n" +
            "  and case\n" +
            "          when ?2 = 'ALL' then status != 'DELETED'\n" +
            "          else status = ?2 end\n" +
            "order by ap.appointment_date desc\n")
    List<Map<String, Object>> viewByPatientIdAndStatus(Integer patientId, String status);

    @Query(nativeQuery = true, value = "select h.hospital_name as \"hospitalName\"\n" +
            "from appointment a\n" +
            "         inner join hospital h on a.hospital_id = h.id\n" +
            "where a.id = ?1")
    String findHospitalByAppointmentId(Integer appointmentId);

    @Query(nativeQuery = true,
    value = "WITH all_months AS (\n" +
            "    SELECT 1 AS month_num, 'Jan' AS month_name UNION ALL\n" +
            "    SELECT 2, 'Feb' UNION ALL\n" +
            "    SELECT 3, 'March' UNION ALL\n" +
            "    SELECT 4, 'April' UNION ALL\n" +
            "    SELECT 5, 'May' UNION ALL\n" +
            "    SELECT 6, 'June' UNION ALL\n" +
            "    SELECT 7, 'July' UNION ALL\n" +
            "    SELECT 8, 'Aug' UNION ALL\n" +
            "    SELECT 9, 'Sept' UNION ALL\n" +
            "    SELECT 10, 'Oct' UNION ALL\n" +
            "    SELECT 11, 'Nov' UNION ALL\n" +
            "    SELECT 12, 'Dec'\n" +
            ")\n" +
            "SELECT\n" +
            "    all_months.month_name AS month,\n" +
            "    COALESCE(cte.\"appointmentCount\", 0) AS \"appointmentCount\"\n" +
            "FROM\n" +
            "    all_months\n" +
            "        LEFT JOIN (\n" +
            "        SELECT\n" +
            "            EXTRACT(MONTH FROM appointment_date) AS month,\n" +
            "            COUNT(id) AS \"appointmentCount\"\n" +
            "        FROM\n" +
            "            appointment\n" +
            "                LEFT JOIN doctor_details dd ON dd.doctor_detail_id = appointment.doctor_detail_id\n" +
            "        WHERE\n" +
            "                dd.doctor_detail_id = ?1\n" +
            "          AND EXTRACT(YEAR FROM appointment_date) = EXTRACT(YEAR FROM CURRENT_DATE)\n" +
            "        GROUP BY\n" +
            "            month\n" +
            "    ) cte ON cte.month = all_months.month_num\n" +
            "ORDER BY\n" +
            "    all_months.month_num")
    List<Map<String, Object>> listAppointmentCount(Integer doctorDetailId);

//    @Query(nativeQuery = true, value = "with cte as (select extract(month from appointment_date) as month,\n" +
//            "                    count(id)                            as \"appointmentCount\"\n" +
//            "             from appointment\n" +
//            "                      inner join doctor_details dd on dd.doctor_detail_id = appointment.doctor_detail_id\n" +
//            "             where dd.doctor_detail_id = ?1\n" +
//            "               and extract(year from appointment_date) = extract(year from current_date)\n" +
//            "             group by month)\n" +
//            "select case\n" +
//            "           when cte.month = 1\n" +
//            "               then 'January'\n" +
//            "           when cte.month = 2\n" +
//            "               then 'February'\n" +
//            "           when cte.month = 3\n" +
//            "               then 'March'\n" +
//            "           when cte.month = 4\n" +
//            "               then 'April'\n" +
//            "           when cte.month = 5\n" +
//            "               then 'May'\n" +
//            "           when cte.month = 6\n" +
//            "               then 'June'\n" +
//            "           when cte.month = 7\n" +
//            "               then 'July'\n" +
//            "           when cte.month = 8\n" +
//            "               then 'August'\n" +
//            "           when cte.month = 9\n" +
//            "               then 'September'\n" +
//            "           when cte.month = 10\n" +
//            "               then 'October'\n" +
//            "           when cte.month = 11\n" +
//            "               then 'November'\n" +
//            "           when cte.month = 12\n" +
//            "               then 'December' end\n" +
//            "           as month,\n" +
//            "       cte.\"appointmentCount\"\n" +
//            "from cte")
//    List<Map<String, Object>> listAppointmentCount(Integer doctorDetailId);


}
