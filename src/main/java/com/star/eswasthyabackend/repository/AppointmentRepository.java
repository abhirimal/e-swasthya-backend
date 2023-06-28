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
            "       ap.reason_for_visit                         as \"reasonForVist\", \n" +
            "       ap.status                                   as \"status\"\n" +
            "from appointment ap\n" +
            "         inner join doctor_details dd on dd.doctor_detail_id = ap.doctor_detail_id\n" +
            "         inner join patient_details pd on pd.patient_detail_id = ap.patient_detail_id\n" +
            "         inner join hospital h on h.id = ap.hospital_id\n" +
            "where status != 'DELETED'\n" +
            "  and ap.id = ?1\n" +
            "order by ap.appointment_date desc\n")
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
            "       ap.status                                   as \"status\"\n" +
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
            "       ap.status                                   as \"status\"\n" +
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
}
