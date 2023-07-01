package com.star.eswasthyabackend.repository.prescription;

import com.star.eswasthyabackend.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Integer> {
    @Query(nativeQuery = true, value = "select p.id,\n" +
            "       p.medicine_name     as \"medicationName\",\n" +
            "       p.dosage_in_unit    as \"dosageInUnit\",\n" +
            "       p.frequency_per_day as \"frequencyPerDay\",\n" +
            "       p.additional_note   as \"additionalNote\",\n" +
            "       p.start_date        as \"startDate\",\n" +
            "       p.end_date          as \"endDate\",\n" +
            "       p.duration_in_days  as \"durationInDays\",\n" +
            "       p.is_active         as \"isActive\",\n" +
            "       p.doctor_detail_id  as \"doctorDetailId\",\n" +
            "       p.patient_detail_id as \"patientDetailId\"\n" +
            "from prescription p\n" +
            "where p.id = ?1")
    Map<String, Object> viewById(Integer id);

    @Query(nativeQuery = true, value = "select *\n" +
            "from prescription p\n" +
            "         inner join diagnosis d on p.diagnosis_id = d.id\n" +
            "where d.appointment_id = ?1")
    List<Prescription> findByAppointmentId(Integer appointmentId);

    @Query(nativeQuery = true,
    value = "SELECT d.name                              as \"districtName\",\n" +
            "       COALESCE(COUNT(p.medicine_name), 0) as \"medicineCount\"\n" +
            "FROM district d\n" +
            "         LEFT JOIN location l ON d.id = l.district_id\n" +
            "         LEFT JOIN patient_details pd ON l.id = pd.location_id\n" +
            "         LEFT JOIN prescription p ON pd.patient_detail_id = p.patient_detail_id\n" +
            "    AND p.medicine_name ILIKE CONCAT('%', ?1, '%')\n" +
            "GROUP BY d.name")
    List<Map<String, Object>> findMedicineCount(String medicineName);
}
