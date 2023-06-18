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
    private String result;

    private String description;

    private LocalDate testDate;

    @OneToOne
    @JoinColumn(name = "patient_detail_id")
    private PatientDetails patientDetail;

    @OneToOne
    @JoinColumn(name = "doctor_detail_id")
    private DoctorDetails recommendedDoctorDetailId;

}
