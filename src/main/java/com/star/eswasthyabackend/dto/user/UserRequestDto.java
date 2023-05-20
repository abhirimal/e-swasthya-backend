package com.star.eswasthyabackend.dto.user;

import com.star.eswasthyabackend.model.Role;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
public class UserRequestDto {
    private Integer id;

    @NotBlank(message = "Email is required.")
    @Pattern(regexp = "[a-zA-Z0-9+_.-]+@[a-z]+[.][a-z]{3}", message = "Enter a valid email address. \n" +
            "Example: xyz@gmail.com")
    private String email;

    @NotBlank(message = "First Name is required")
    private String firstName;

    @NotBlank(message = "Last Name is required")
    private String lastName;

    @NotBlank(message = "Password is required")
    @Pattern(regexp ="^[a-zA-z0-9]{8,}$",message = "Password should be of 8 digits")
    private String password;

    private List<Integer> rolesId;
}
