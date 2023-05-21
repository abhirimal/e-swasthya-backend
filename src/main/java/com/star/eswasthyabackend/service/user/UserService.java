package com.star.eswasthyabackend.service.user;

import com.star.eswasthyabackend.dto.user.UserResetPasswordRequest;
import com.star.eswasthyabackend.dto.user.UserSignUpRequest;

public interface UserService {

     Integer addNewUser(UserSignUpRequest userSignUpRequest);

    Boolean verifyAccount(Integer id, String token);

    void resendVerificationLink(Integer id);

    void resetPasswordRequest(String email);

    void changePassword(UserResetPasswordRequest passwordRequest);

    String verifyResetPasswordLink(Integer id, String token);
}
