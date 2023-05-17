package com.star.eswasthyabackend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserAccountVerificationService {

    private final EmailSenderService emailSenderService;
    private final GenerateTokenService generateTokenService;

    public void verifyAccount(String email, Integer userId, String token){

        emailSenderService.sendEmail(email,
                "Please click the link below to verify your account. The link expires in 30 minutes. \n" +
                        "localhost:8080/"+userId+"/"+token,
                "Verify Your Account");
    }
}
