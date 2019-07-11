package br.com.architecture.authservice.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmailFoundException extends RuntimeException {

    public EmailFoundException() {
        super("Email already exists!");
    }

    public EmailFoundException(String message) {
        super(message);
    }
}