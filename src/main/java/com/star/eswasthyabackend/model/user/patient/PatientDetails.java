package com.star.eswasthyabackend.model.user.patient;

import com.star.eswasthyabackend.model.user.User;
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
public class PatientDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String citizenshipNo;

    private Integer medicalRecordId;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private String weight;

    private String bloodGroup;

    private String address;

    private LocalDate dateOfBirth;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
