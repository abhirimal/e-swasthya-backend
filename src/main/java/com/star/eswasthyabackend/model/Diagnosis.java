package com.star.eswasthyabackend.model;

import com.star.eswasthyabackend.model.doctor.DoctorDetails;
import com.star.eswasthyabackend.model.patient.PatientDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.time.LocalDate;
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

    private String diseaseType;

    @Column(columnDefinition = "TEXT")
    private String diagnosisDescription;

    private LocalDate date;

    @OneToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    @OneToOne
    @JoinColumn(name = "patient_detail_id")
    private PatientDetails patientDetail;

    @OneToOne
    @JoinColumn(name = "doctor_detail_id")
    private DoctorDetails doctorDetail;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "diagnosis1")
    private List<TestResult> testResultList;

    @OneToMany(mappedBy = "diagnosis")
    private List<Prescription> prescriptionList;
}
