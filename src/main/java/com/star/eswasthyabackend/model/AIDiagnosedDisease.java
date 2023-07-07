package com.star.eswasthyabackend.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ai_diagnosed_disease")
public class AIDiagnosedDisease {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ai_diagnosed_disease_seq_gen")
    @SequenceGenerator(name = "ai_diagnosed_disease_seq_gen", sequenceName = "ai_diagnosed_disease_seq", allocationSize = 1)
    private Integer id;

    private String name;

    private String imagePath;

    private Boolean isDetected;

    @OneToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

}
