package com.star.eswasthyabackend.model;

import com.star.eswasthyabackend.model.patient.PatientDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AllergicMedicine {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "allergy_seq_gen")
    @SequenceGenerator(name = "allergy_seq_gen", sequenceName = "allergy_seq", allocationSize = 1)
    private Integer id;

    private String allergicMedicineName;

    @ManyToOne
    @JoinColumn(name = "patient_detail_id")
    private PatientDetails patientDetails;
}
