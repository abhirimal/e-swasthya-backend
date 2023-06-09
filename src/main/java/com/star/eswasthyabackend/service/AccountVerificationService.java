package com.star.eswasthyabackend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountVerificationService {

    private final EmailSenderService emailSenderService;
    public void verifyOrResetAccount(String email, Integer userId, String token){

        emailSenderService.sendEmail(email,
                "Please click the link below to verify your account. The link expires in 30 minutes. \n" +
                        "localhost:3000/VerifyAccountLink/"+userId+"/"+token,
                "Verify Your Account");
    }
}
