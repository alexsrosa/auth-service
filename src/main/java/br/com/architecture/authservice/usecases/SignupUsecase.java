package br.com.architecture.authservice.usecases;

import br.com.architecture.authservice.domain.entities.UserEntity;
import br.com.architecture.authservice.infrastructure.configuration.security.TokenService;
import br.com.architecture.authservice.infrastructure.entrypoints.converters.UserEntityToUserOut;
import br.com.architecture.authservice.infrastructure.entrypoints.converters.UserInToUserEntity;
import br.com.architecture.authservice.infrastructure.entrypoints.dtos.UserInDto;
import br.com.architecture.authservice.infrastructure.entrypoints.dtos.UserOutDto;
import br.com.architecture.authservice.infrastructure.services.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class SignupUsecase {

    private final UserService userService;
    private final UserInToUserEntity userInToUserEntity;
    private final UserEntityToUserOut userEntityToUserOut;
    private final TokenService tokenService;
    private final AuthenticationManager authManager;

    public SignupUsecase(UserService userService,
                         UserInToUserEntity userInToUserEntity,
                         UserEntityToUserOut userEntityToUserIn,
                         TokenService tokenService, AuthenticationManager authManager) {
        this.userService = userService;
        this.userInToUserEntity = userInToUserEntity;
        this.userEntityToUserOut = userEntityToUserIn;
        this.tokenService = tokenService;
        this.authManager = authManager;
    }

    public Optional<UserOutDto> create(UserInDto userInDto) {
        Optional<UserEntity> optionalUserEntity = userService.save(userInToUserEntity.convert(userInDto));

        if(optionalUserEntity.isPresent()){
            UserEntity userEntity = optionalUserEntity.get();
            UserOutDto outDto = userEntityToUserOut.convert(userEntity);

            if(Objects.nonNull(outDto)){

                Authentication authentication =
                        authManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                        userInDto.getEmail(), userInDto.getPassword()));

                outDto.setToken(tokenService.generateToken(authentication));
                outDto.setTokenType(TokenService.BEAREN);
            }

            return Optional.ofNullable(outDto);
        }

        return Optional.empty();
    }
}
