package br.com.architecture.authservice.usecases;

import br.com.architecture.authservice.infrastructure.configuration.security.TokenService;
import br.com.architecture.authservice.infrastructure.entrypoints.dtos.AuthInDto;
import br.com.architecture.authservice.infrastructure.entrypoints.dtos.AuthOutDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthUserUsecase {

    private final AuthenticationManager authManager;
    private final TokenService tokenService;

    public AuthUserUsecase(AuthenticationManager authManager, TokenService tokenService) {
        this.authManager = authManager;
        this.tokenService = tokenService;
    }

    public Optional<AuthOutDto> authentication(AuthInDto inDto) {
        try {
            Authentication authentication = authManager.authenticate(inDto.convertToUserPasswdAuth());
            String token = tokenService.generateToken(authentication);
            return Optional.of(new AuthOutDto(token, "Bearer"));
        } catch (AuthenticationException e) {
            return Optional.empty();
        }
    }
}
