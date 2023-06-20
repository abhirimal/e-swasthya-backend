package com.star.eswasthyabackend.model;

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
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Medication_SEQ_GEN")
    @SequenceGenerator(name = "Medication_SEQ_GEN", sequenceName = "Medication_SEQ", allocationSize = 1)
    private Integer id;

    private String medicineName;

    private Double dosageInUnit;

    private String frequencyPerDay;

    private String additionalNote;

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer durationInDays;

    private Boolean isActive;

    @OneToOne
    @JoinColumn(name = "patient_detail_id")
    private PatientDetails patientDetail;

    @OneToOne
    @JoinColumn(name = "doctor_detail_id")
    private DoctorDetails doctorDetail;
}
