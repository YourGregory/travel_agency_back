package com.kursova.travel.security.exception;

import org.springframework.security.core.AuthenticationException;

public class InvalidTokenException extends AuthenticationException {

    public InvalidTokenException(String explanation) {
        super(explanation);
    }

}
