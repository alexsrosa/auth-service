package br.com.architecture.authservice.infrastructure.entrypoints.controllers;

import br.com.architecture.authservice.infrastructure.configuration.security.TokenService;
import br.com.architecture.authservice.infrastructure.entrypoints.dtos.AuthInDto;
import br.com.architecture.authservice.infrastructure.entrypoints.dtos.AuthOutDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authManager, TokenService tokenService) {
        this.authManager = authManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<AuthOutDto> auth(@RequestBody @Valid AuthInDto inDto) {

        try {
            Authentication authentication = authManager.authenticate(inDto.convertToUserPasswdAuth());
            String token = tokenService.generateToken(authentication);
            return ResponseEntity.ok(new AuthOutDto(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
