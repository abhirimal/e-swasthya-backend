package com.star.eswasthyabackend.model;

import com.star.eswasthyabackend.model.doctor.DoctorDetails;
import com.star.eswasthyabackend.model.patient.PatientDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Diagnosis {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Diagnosis_SEQ_GEN")
    @SequenceGenerator(name = "Diagnosis_SEQ_GEN", sequenceName = "Diagnosis_SEQ", allocationSize = 1)
    private Integer id;

    private String diseaseName;

    private String diagnosisDescription;

    @OneToOne
    @JoinColumn(name = "patient_detail_id")
    private PatientDetails patientDetail;

    @OneToOne
    @JoinColumn(name = "doctor_detail_id")
    private DoctorDetails doctorDetail;

    @OneToMany(mappedBy = "diagnosis")
    private List<TestResult> testResult;
}
