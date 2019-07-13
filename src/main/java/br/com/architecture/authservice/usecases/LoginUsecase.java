package br.com.architecture.authservice.usecases;

import br.com.architecture.authservice.infrastructure.configuration.security.TokenService;
import br.com.architecture.authservice.infrastructure.entrypoints.dtos.LoginInDto;
import br.com.architecture.authservice.infrastructure.entrypoints.dtos.LoginOutDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginUsecase {

    private final AuthenticationManager authManager;
    private final TokenService tokenService;

    public LoginUsecase(AuthenticationManager authManager, TokenService tokenService) {
        this.authManager = authManager;
        this.tokenService = tokenService;
    }

    public Optional<LoginOutDto> authentication(LoginInDto inDto) {
        try {
            Authentication authentication = authManager.authenticate(inDto.convertToUserPasswdAuth());
            String token = tokenService.generateToken(authentication);
            return Optional.of(new LoginOutDto(token, "Bearer"));
        } catch (AuthenticationException e) {
            return Optional.empty();
        }
    }
}
