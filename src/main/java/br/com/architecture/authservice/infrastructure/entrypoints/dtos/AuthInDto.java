package br.com.architecture.authservice.infrastructure.entrypoints.dtos;


import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AuthInDto {

    private final ModelMapper modelMapper;

    @NotNull @Email
    private String email;

    @NotNull
    private String password;

    public AuthInDto(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UsernamePasswordAuthenticationToken convert() {
        return modelMapper.map(this, UsernamePasswordAuthenticationToken.class);
    }
}
