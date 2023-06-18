package com.star.eswasthyabackend.repository;

import com.star.eswasthyabackend.model.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Integer> {

    @Query(nativeQuery = true, value = "select tr.id          as \"testId\",\n" +
            "       tr.test_name   as \"testName\",\n" +
            "       tr.result      as \"testResult\",\n" +
            "       tr.description as \"testDescription\",\n" +
            "       tr.test_date   as \"testDate\",\n" +
            "       tr.image_path  as \"imagePath\"\n" +
            "from test_result tr\n" +
            "where tr.id = ?1")
    Map<String, Object> findTestResultById(Integer id);

    @Query(nativeQuery = true, value = "select distinct pd.patient_detail_id as \"patientDetailId\",\n" +
            "                pd.first_name        as \"firstName\",\n" +
            "                pd.last_name         as \"lastname\",\n" +
            "                pd.email             as \"email\"\n" +
            "from patient_details pd\n" +
            "         inner join test_result tr on pd.patient_detail_id = tr.patient_detail_id\n" +
            "where tr.id = ?1")
    Map<String, Object> findPatientByTestResultId(Integer testResultId);

    @Query(nativeQuery = true, value = "select id          as \"id\",\n" +
            "       test_name   as \"testName\",\n" +
            "       result      as \"testResult\",\n" +
            "       description as \"description\",\n" +
            "       test_date   as \"testDate\"\n" +
            "from test_result\n" +
            "where patient_detail_id = ?1")
    List<Map<String, Object>> findAllByPatientId(Integer patientId);
}
