package com.star.eswasthyabackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`users`")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String firstName;

    private String lastName;

    private String password;

    @Column(nullable = false)
    private String email;

    private String verificationToken;

    private Boolean isVerified;

    private LocalTime verifyTokenGenTime;

    private String resetPasswordToken;

    private Boolean resetPasswordEnabled;

    private LocalTime resetPasswordTokenGenTime;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roles;

}
