package com.star.eswasthyabackend.dto.sms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SociairSendSmsRequest {

    private String message;
    private String mobile;
}
