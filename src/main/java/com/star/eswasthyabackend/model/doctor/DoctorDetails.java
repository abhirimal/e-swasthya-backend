package com.star.eswasthyabackend.model.doctor;

import com.star.eswasthyabackend.model.Hospital;
import com.star.eswasthyabackend.model.User;
import com.star.eswasthyabackend.model.location.Location;
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
public class DoctorDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer doctorDetailId;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String nmcLicenseNo;

    private String education;

    private String specialization;

    private String experience;

    private String gender;

    @ManyToMany
    @JoinTable(name = "doctor_hospital",
            joinColumns = { @JoinColumn(name = "doctor_id")},
            inverseJoinColumns = {@JoinColumn(name = "hospital_id")})
    private List<Hospital> associatedHospitalList;

    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
