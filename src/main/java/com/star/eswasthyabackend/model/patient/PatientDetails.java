package com.star.eswasthyabackend.model.patient;

import com.star.eswasthyabackend.model.User;
import com.star.eswasthyabackend.model.Vaccination;
import com.star.eswasthyabackend.model.location.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer patientId;

    @Column(nullable = false)
    private String citizenshipNo;

    private String medicalRecordNumber;

    private String firstName;

    private String lastName;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits without any special character.")
    private String phoneNumber;

    private String email;

    private String weight;

    private String bloodGroup;

    private String filePath;

    private LocalDate dateOfBirth;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

}
