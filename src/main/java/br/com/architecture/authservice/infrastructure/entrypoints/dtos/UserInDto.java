package br.com.architecture.authservice.infrastructure.entrypoints.dtos;

import br.com.architecture.authservice.domain.entities.UserEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class UserInDto {

    @NotNull
    private String name;

    @NotNull @Email
    private String email;

    @NotNull
    private String password;
}
