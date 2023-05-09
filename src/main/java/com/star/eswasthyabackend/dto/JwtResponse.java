package com.star.eswasthyabackend.dto;

import lombok.Getter;

@Getter
public class JwtResponse {

    private String jwt;

    public JwtResponse(String jwt){
        this.jwt = jwt;
    }
}
