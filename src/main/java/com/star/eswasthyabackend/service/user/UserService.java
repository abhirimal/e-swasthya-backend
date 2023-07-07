package com.star.eswasthyabackend.service.user;

import com.star.eswasthyabackend.dto.user.UserResetPasswordRequest;
import com.star.eswasthyabackend.dto.user.UserSignUpRequest;
import com.star.eswasthyabackend.model.User;

public interface UserService {

     Integer addNewUser(UserSignUpRequest userSignUpRequest);

    Boolean verifyAccount(Integer id, String token);

    void resendVerificationLink(String email);

    void resetPasswordRequest(String email);

    void changePassword(UserResetPasswordRequest passwordRequest);

    Integer verifyResetPasswordLink(Integer id, String token);

    void saveCustomUser(User user);

    String getEmailByUserId(Integer userId);
}
