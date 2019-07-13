package br.com.architecture.authservice.infrastructure.entrypoints.controllers;

import br.com.architecture.authservice.infrastructure.entrypoints.dtos.LoginInDto;
import br.com.architecture.authservice.infrastructure.entrypoints.dtos.LoginOutDto;
import br.com.architecture.authservice.infrastructure.exceptions.UserNotFoundException;
import br.com.architecture.authservice.usecases.LoginUsecase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginUsecase loginUsecase;

    public LoginController(LoginUsecase loginUsecase) {
        this.loginUsecase = loginUsecase;
    }

    @PostMapping
    public ResponseEntity<LoginOutDto> login(@RequestBody @Valid LoginInDto inDto) {

        return loginUsecase.authentication(inDto)
                .map(ResponseEntity::ok)
                .orElseThrow(UserNotFoundException::new);
    }
}
