package com.star.eswasthyabackend.utility;

import com.star.eswasthyabackend.model.User;
import com.star.eswasthyabackend.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationUtil {

    private final UserRepository userRepository;

    public String getUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public Integer getUserId(){
        String username = getUserName();
        User user = userRepository.loadUserByUsername(username);
        return user.getId();
    }
}