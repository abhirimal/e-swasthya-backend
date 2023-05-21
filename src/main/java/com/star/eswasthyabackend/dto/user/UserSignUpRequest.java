package com.star.eswasthyabackend.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
public class UserSignUpRequest {
    private Integer id;

    @NotBlank(message = "Email is required.")
    @Pattern(regexp = "[a-zA-Z0-9+_.-]+@[a-z]+[.][a-z]{3}", message = "Enter a valid email address." +
            "Example: xyz@gmail.com")
    private String email;

    @NotBlank(message = "First Name is required")
    @Size(min = 3, message = "First Name should contain atleast 3 letters.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name should only contain alphabetic characters.")
    private String firstName;

    @NotBlank(message = "Last Name is required.")
    @Size(min = 2, message = "Last Name should contain atleast 2 letters.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name should only contain alphabetic characters.")
    private String lastName;

    @NotBlank(message = "Password is required.")
    @Pattern(regexp ="^[a-zA-z0-9]{8,}$",message = "Password should be of 8 digits.")
    private String password;

    @NotEmpty
    private List<Integer> rolesId;
}
