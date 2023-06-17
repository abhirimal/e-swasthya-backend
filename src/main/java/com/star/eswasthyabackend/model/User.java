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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Users_SEQ_GEN")
    @SequenceGenerator(name = "Users_SEQ_GEN", sequenceName = "Users_SEQ", allocationSize = 1)
    @Column( name = "id", nullable = false)
    private Integer id;

    @Column(name = "first_name", nullable = false, length = 100)
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

    private Boolean isFormFilled;

    private String role;

    public User(Integer id, String firstName, String lastName, String password, String email, Boolean isVerified, Boolean isFormFilled, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.isVerified = isVerified;
        this. isFormFilled = isFormFilled;
        this.role = role;
    }

//    @ManyToMany
//    @JoinTable(name = "user_role",
//            joinColumns = {@JoinColumn(name = "user_id")},
//            inverseJoinColumns = {@JoinColumn(name = "role_id")})
//    private List<Role> roles;

}
