package com.star.eswasthyabackend.model;

import com.star.eswasthyabackend.model.user.User;
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
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private Integer roleId;

    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private List<User> userList;

//    public static final String ROLE_ADMIN = "ADMIN";
//    public static final Integer ROLE_ID_ADMIN =1;
//    public static final String ROLE_PATIENT = "PATIENT";
//    public static final Integer ROLE_ID_PATIENT =2;
//    public static final String ROLE_DOCTOR = "DOCTOR";
//    public static final Integer ROLE_ID_DOCTOR =3;
//    public static final String ROLE_SUPPORT = "SUPPORT";
//    public static final Integer ROLE_ID_SUPPORT =4;


}
