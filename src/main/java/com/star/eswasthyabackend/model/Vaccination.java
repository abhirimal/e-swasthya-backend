package com.star.eswasthyabackend.model;

import com.star.eswasthyabackend.model.patient.PatientDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Vaccination {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String vaccineName;

    private LocalDate vaccinationDate;

    private Integer dosage;

    @ManyToMany
    @JoinTable(name = "patient_vaccination",
            joinColumns = {@JoinColumn(name = "patient_id")},
            inverseJoinColumns = {@JoinColumn(name = "vaccination_id")})
    private List<PatientDetails> patientDetails;
}