package com.star.eswasthyabackend.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class UserResetPasswordRequest {

    private Integer id;

    @NotBlank(message = "Password is required.")
    @Pattern(regexp ="^[a-zA-z0-9]{8,}$",message = "Password should be of 8 digits.")
    private String newPassword;

}
