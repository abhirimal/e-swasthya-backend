package com.star.eswasthyabackend.exception;

public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException(String message){
        super(message);
    }
}
