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
            "       concat_ws(' ', pd.first_name, pd.last_name) as \"patientName\",\n" +
            "       pd.patient_detail_id                        as \"patientId\",\n" +
            "       pd.medical_record_number                    as \"medicalRecordNumber\",\n" +
            "       ap.appointment_date                         as \"appointmentDate\",\n" +
            "       ap.appointment_time                         as \"appointmentTime\",\n" +
            "       ap.hospital_name                            as \"hospitalName\"\n" +
            "from appointment ap\n" +
            "         inner join doctor_details dd on dd.doctor_detail_id = ap.doctor_detail_id\n" +
            "         inner join patient_details pd on pd.patient_detail_id = ap.patient_detail_id\n" +
            "where ap.is_active = true\n" +
            "  and ap.isVerifiedBySms = true\n" +
            "  and ap.id = ?1")
    Map<String, Object> viewById(Integer appointmentId);

    @Query(nativeQuery = true, value = "select ap.id                                       as \"appointmentId\",\n" +
            "       concat_ws(' ', dd.first_name, dd.last_name) as \"doctorName\",\n" +
            "       dd.doctor_detail_id                         as \"doctorId\",\n" +
            "       concat_ws(' ', pd.first_name, pd.last_name) as \"patientName\",\n" +
            "       pd.patient_detail_id                        as \"patientId\",\n" +
            "       pd.medical_record_number                    as \"medicalRecordNumber\",\n" +
            "       ap.appointment_date                         as \"appointmentDate\",\n" +
            "       ap.appointment_time                         as \"appointmentTime\",\n" +
            "       ap.hospital_name                            as \"hospitalName\"\n" +
            "from appointment ap\n" +
            "         inner join doctor_details dd on dd.doctor_detail_id = ap.doctor_detail_id\n" +
            "         inner join patient_details pd on pd.id = ap.patient_detail_id\n" +
            "where ap.is_active = true\n" +
            "  and ap.isVerifiedBySms = true\n" +
            "  and ap.doctor_detail_id = ?1")
    List<Map<String, Object>> viewByDoctorId(Integer doctorId);

    @Query(nativeQuery = true, value = "select ap.id                                       as \"appointmentId\",\n" +
            "       concat_ws(' ', dd.first_name, dd.last_name) as \"doctorName\",\n" +
            "       dd.doctor_detail_id                         as \"doctorId\",\n" +
            "       concat_ws(' ', pd.first_name, pd.last_name) as \"patientName\",\n" +
            "       pd.patient_detail_id                        as \"patientId\",\n" +
            "       pd.medical_record_number                    as \"medicalRecordNumber\",\n" +
            "       ap.appointment_date                         as \"appointmentDate\",\n" +
            "       ap.appointment_time                         as \"appointmentTime\",\n" +
            "       ap.hospital_name                            as \"hospitalName\"\n" +
            "from appointment ap\n" +
            "         inner join doctor_details dd on dd.doctor_detail_id = ap.doctor_detail_id\n" +
            "         inner join patient_details pd on pd.patient_detail_id = ap.patient_detail_id\n" +
            "where ap.is_active is true\n" +
            "  and pd.patient_detail_id = ?1")
    List<Map<String, Object>> viewByPatientId(Integer patientId);
}
