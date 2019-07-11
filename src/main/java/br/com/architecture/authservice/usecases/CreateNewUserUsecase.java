package br.com.architecture.authservice.usecases;

import br.com.architecture.authservice.infrastructure.entrypoints.converters.UserEntityToUserOut;
import br.com.architecture.authservice.infrastructure.entrypoints.converters.UserInToUserEntity;
import br.com.architecture.authservice.infrastructure.entrypoints.dtos.UserInDto;
import br.com.architecture.authservice.infrastructure.entrypoints.dtos.UserOutDto;
import br.com.architecture.authservice.infrastructure.services.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateNewUserUsecase {

    private UserService userService;
    private UserInToUserEntity userInToUserEntity;
    private UserEntityToUserOut userEntityToUserOut;

    public CreateNewUserUsecase(UserService userService,
                                UserInToUserEntity userInToUserEntity,
                                UserEntityToUserOut userEntityToUserIn) {
        this.userService = userService;
        this.userInToUserEntity = userInToUserEntity;
        this.userEntityToUserOut = userEntityToUserIn;
    }

    public Optional<UserOutDto> create(UserInDto userInDto) {
        return userService.save(userInToUserEntity.convert(userInDto))
                .map(userEntityToUserOut::convert);
    }
}
