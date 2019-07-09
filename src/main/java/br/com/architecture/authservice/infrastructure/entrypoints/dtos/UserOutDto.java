package br.com.architecture.authservice.infrastructure.entrypoints.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserOutDto {

    private String id;

    private String name;

    private String email;
}
