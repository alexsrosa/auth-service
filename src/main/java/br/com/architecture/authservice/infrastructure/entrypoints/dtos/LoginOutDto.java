package br.com.architecture.authservice.infrastructure.entrypoints.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginOutDto {

    private String email;

    private String token;

    private String tokenType;

    public LoginOutDto(String token, String tokenType) {
        this.token = token;
        this.tokenType = tokenType;
    }
}
