package com.star.eswasthyabackend.service;

import com.star.eswasthyabackend.exception.AppException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderService {
    private final JavaMailSender javaMailSender;

    public void sendEmail(String toEmail,
                          String body,
                          String subject) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("info.eswasthya.app@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        try {
            javaMailSender.send(message);
            System.out.println("Mail Sent! ");
        } catch (Exception e) {
            throw new AppException("Email not sent. Invalid Email.", HttpStatus.BAD_REQUEST);
        }
    }
}
