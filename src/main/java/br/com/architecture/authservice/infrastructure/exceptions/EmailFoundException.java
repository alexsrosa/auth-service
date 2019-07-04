package br.com.architecture.authservice.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailFoundException extends RuntimeException {

    public EmailFoundException() {
        super("E-mail já existente");
    }

    public EmailFoundException(String message) {
        super(message);
    }
}