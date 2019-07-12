package br.com.architecture.authservice.infrastructure.entrypoints.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class UserInDto {

    private String firstName;

    private String lastName;

    @NotNull @Email
    private String email;

    @NotNull
    private String password;
}
