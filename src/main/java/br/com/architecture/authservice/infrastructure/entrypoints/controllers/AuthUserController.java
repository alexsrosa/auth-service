package br.com.architecture.authservice.infrastructure.entrypoints.controllers;

import br.com.architecture.authservice.infrastructure.entrypoints.dtos.AuthInDto;
import br.com.architecture.authservice.infrastructure.entrypoints.dtos.AuthOutDto;
import br.com.architecture.authservice.infrastructure.exceptions.UserNotFoundException;
import br.com.architecture.authservice.usecases.AuthUserUsecase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthUserController {

    private final AuthUserUsecase authUserUsecase;

    public AuthUserController(AuthUserUsecase authUserUsecase) {
        this.authUserUsecase = authUserUsecase;
    }

    @PostMapping
    public ResponseEntity<AuthOutDto> auth(@RequestBody @Valid AuthInDto inDto) {

        return authUserUsecase.authentication(inDto)
                .map(ResponseEntity::ok)
                .orElseThrow(UserNotFoundException::new);
    }
}
