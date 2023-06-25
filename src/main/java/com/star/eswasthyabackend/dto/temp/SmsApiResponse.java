package com.star.eswasthyabackend.dto.temp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SmsApiResponse {

    private Boolean status;

    private String message;

    private Integer data;

    private String otp;
}
