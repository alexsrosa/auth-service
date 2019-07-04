package br.com.architecture.authservice.infrastructure.handler;

/**
 * @author alexsrosa
 */
public class ExceptionResponse {

    private String message;

    public ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
