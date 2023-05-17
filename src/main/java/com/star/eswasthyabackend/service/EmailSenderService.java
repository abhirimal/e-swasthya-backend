package com.star.eswasthyabackend.service;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
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

        javaMailSender.send(message);
        System.out.println("Mail Sent! ");

    }
}
