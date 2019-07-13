package br.com.architecture.authservice.infrastructure.entrypoints.dtos;


import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginInDto {

    @NotNull @Email
    private String email;

    @NotNull
    private String password;

    public UsernamePasswordAuthenticationToken convertToUserPasswdAuth() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
