package com.star.eswasthyabackend.service.user;

import com.star.eswasthyabackend.dto.user.UserRequestDto;
import com.star.eswasthyabackend.model.User;

public interface UserService {

     Integer addNewUser(UserRequestDto userRequestDto);

    Boolean verifyAccount(Integer id, String token);

    void resendVerificationLink(Integer id);
}
