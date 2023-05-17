package com.star.eswasthyabackend.dto.user;

import com.star.eswasthyabackend.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRequestDto {
    private Integer id;

    private String email;

    private String username;

    private String password;

    private List<Integer> rolesId;
}
