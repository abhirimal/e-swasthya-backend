package com.star.eswasthyabackend.service.sms;

public interface SmsService {
    void sendSms(String phoneNo, String message);
}
