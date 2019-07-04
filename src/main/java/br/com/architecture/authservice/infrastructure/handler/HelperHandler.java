package br.com.architecture.authservice.infrastructure.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author alexsrosa
 */
public interface HelperHandler {

    default ResponseEntity<Object> createReturn(String message, HttpStatus httpStatus){
        return new ResponseEntity(new ExceptionResponse(message), httpStatus);
    }

    default ResponseEntity<Object> createReturn(List<String> message, HttpStatus httpStatus){
        return new ResponseEntity(new ExceptionListResponse(message), httpStatus);
    }
}
