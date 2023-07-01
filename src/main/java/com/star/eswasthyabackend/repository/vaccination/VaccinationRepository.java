package com.star.eswasthyabackend.repository.vaccination;

import com.star.eswasthyabackend.model.vaccination.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface VaccinationRepository extends JpaRepository<Vaccination, Integer> {


    @Query(value = "select id,\n" +
            "       dosage,\n" +
            "       vaccine_name       as \"vaccineName\",\n" +
            "       vaccination_date   as \"vaccinationDate\",\n" +
            "       patient_detail_id as \"patientId\"\n" +
            "from vaccination\n" +
            "where id = ?1", nativeQuery = true)
    Map<String, Object> findByVaccinationId(Integer vaccinationId);

    @Query(value = "select id,\n" +
            "       dosage,\n" +
            "       vaccine_name       as \"vaccineName\",\n" +
            "       vaccination_date   as \"vaccinationDate\",\n" +
            "       patient_detail_id as \"patientId\"\n" +
            "from vaccination\n", nativeQuery = true)
    List<Map<String, Object>> findAllVaccinationReport();

    @Query(value = "select id,\n" +
            "       dosage,\n" +
            "       vaccine_name       as \"vaccineName\",\n" +
            "       vaccination_date   as \"vaccinationDate\",\n" +
            "       patient_detail_id as \"patientId\"\n" +
            "from vaccination\n" +
            "where patient_detail_id = ?1", nativeQuery = true)
    List<Map<String, Object>> findByPatientId(Integer patientId);

    @Query(nativeQuery = true,
        value = "select d.name                         as \"districtName\",\n" +
            "       coalesce(count(v.vaccine_name), 0) as \"vaccinationCount\"\n" +
            "from district d\n" +
            "         left join location l on d.id = l.district_id\n" +
            "         left join patient_details pd on l.id = pd.location_id\n" +
            "         left join vaccination v on pd.patient_detail_id = v.patient_detail_id\n" +
            "where v.vaccine_name ilike concat('%', ?1, '%')\n" +
            "   or v.vaccine_name is null\n" +
            "group by d.name")
    List<Map<String, Object>> findVaccinationCount(String vaccineName);
}
