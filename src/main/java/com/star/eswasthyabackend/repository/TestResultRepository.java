package com.star.eswasthyabackend.repository;

import com.star.eswasthyabackend.model.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Integer> {

    @Query(nativeQuery = true, value = "select tr.id                as id,\n" +
            "       tr.test_name         as \"testName\",\n" +
            "       tr.test_type         as \"testType\",\n" +
            "       tr.result            as \"testResult\",\n" +
            "       tr.description       as \"description\",\n" +
            "       tr.test_date         as \"testDate\",\n" +
            "       dd.doctor_detail_id  as \"doctorDetailId\",\n" +
            "       pd.patient_detail_id as \"patientDetailId\"\n" +
            "from test_result tr\n" +
            "         inner join doctor_details dd on dd.doctor_detail_id = tr.doctor_detail_id\n" +
            "         inner join patient_details pd on pd.patient_detail_id = tr.patient_detail_id\n" +
            "where pd.patient_detail_id = ?1")
    List<Map<String, Object>> findAllByPatientId(Integer patientId);
}
