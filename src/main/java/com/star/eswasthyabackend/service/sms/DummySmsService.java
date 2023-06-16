package com.star.eswasthyabackend.service.sms;

import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;

@Service
public class DummySmsService implements SmsService{

    @Override
    public void sendSms(String phoneNo, String message) {
        System.out.println("Sending sms to "+phoneNo+" "+message);
    }

}
