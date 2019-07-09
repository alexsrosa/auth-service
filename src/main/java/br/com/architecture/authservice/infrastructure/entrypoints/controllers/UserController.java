package br.com.architecture.authservice.infrastructure.entrypoints.controllers;

import br.com.architecture.authservice.infrastructure.entrypoints.dtos.UserInDto;
import br.com.architecture.authservice.infrastructure.entrypoints.dtos.UserOutDto;
import br.com.architecture.authservice.infrastructure.exceptions.GeneralException;
import br.com.architecture.authservice.usecases.CreateNewUserUsecase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private CreateNewUserUsecase createNewUserUsecase;

    public UserController(CreateNewUserUsecase createNewUserUsecase) {
        this.createNewUserUsecase = createNewUserUsecase;
    }

    @PostMapping
    public ResponseEntity<UserOutDto> create(@RequestBody @Valid UserInDto userInDto,
                                             UriComponentsBuilder uri) {
        return createNewUserUsecase.create(userInDto)
                .map((UserOutDto out) ->
                        ResponseEntity.created(uri.path("/user")
                                .buildAndExpand(out.getId()).toUri()).body(out))
                .orElseThrow(() -> new GeneralException("Error to create new user!"));
    }
}
