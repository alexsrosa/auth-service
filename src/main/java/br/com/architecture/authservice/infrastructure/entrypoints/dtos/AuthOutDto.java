package br.com.architecture.authservice.infrastructure.entrypoints.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthOutDto {

    private String email;

    private String token;

    private String tokenType;

    public AuthOutDto(String token, String tokenType) {
        this.token = token;
        this.tokenType = tokenType;
    }
}
