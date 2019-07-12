package br.com.architecture.authservice.infrastructure.entrypoints.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserOutDto {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String token;
    private String tokenType;
}
