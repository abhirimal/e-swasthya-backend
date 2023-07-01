package com.star.eswasthyabackend.model;

import com.star.eswasthyabackend.dto.testResult.TestResultRequestDto;
import com.star.eswasthyabackend.model.diagnosis.Diagnosis;
import com.star.eswasthyabackend.model.doctor.DoctorDetails;
import com.star.eswasthyabackend.model.patient.PatientDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Test_Result_SEQ_GEN")
    @SequenceGenerator(name = "Test_Result_SEQ_GEN", sequenceName = "Test_Result_SEQ", allocationSize = 1)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String testName;

    @Column(nullable = false)
    private String testType;

    @Column(nullable = false)
    private String result;

    private String description;

    private LocalDate testDate;

    private String imagePath;

    @OneToOne
    @JoinColumn(name = "patient_detail_id")
    private PatientDetails patientDetail;

    @OneToOne
    @JoinColumn(name = "doctor_detail_id")
    private DoctorDetails recommendedDoctorDetail;

    @ManyToOne
    @JoinColumn(name = "diagnosis_id")
    private Diagnosis diagnosis1;

    public TestResult(TestResultRequestDto newTestResult, Diagnosis diagnosis1, DoctorDetails doctorDetail, PatientDetails patientDetail) {
        testName = newTestResult.getTestName();
        testType = newTestResult.getTestType();
        result = newTestResult.getResult();
        description = newTestResult.getDescription();
        testDate = newTestResult.getTestDate();
        imagePath = newTestResult.getImagePath();
        this.patientDetail = patientDetail;
        this.recommendedDoctorDetail = doctorDetail;
        this.diagnosis1 = diagnosis1;
    }
}
