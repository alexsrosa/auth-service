package br.com.architecture.authservice.infrastructure.handler;

import java.util.List;

/**
 * @author alexsrosa
 */
public class ExceptionListResponse {

    private List<String> message;

    public ExceptionListResponse(List<String> message) {
        this.message = message;
    }

    public List<String> getMessage() {
        return message;
    }
}
