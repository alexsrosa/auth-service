package br.com.architecture.authservice.infrastructure.entrypoints.controllers;

import br.com.architecture.authservice.infrastructure.entrypoints.dtos.UserInDto;
import br.com.architecture.authservice.infrastructure.entrypoints.dtos.UserOutDto;
import br.com.architecture.authservice.infrastructure.exceptions.GeneralException;
import br.com.architecture.authservice.usecases.SignupUsecase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/signup")
public class SignupController {

    private SignupUsecase createNewUserUsecase;

    public SignupController(SignupUsecase createNewUserUsecase) {
        this.createNewUserUsecase = createNewUserUsecase;
    }

    @PostMapping
    public ResponseEntity<UserOutDto> signup(@RequestBody @Valid UserInDto userInDto,
                                             UriComponentsBuilder uri) {
        return createNewUserUsecase.create(userInDto)
                .map((UserOutDto out) ->
                        ResponseEntity.created(uri.path("/signup")
                                .buildAndExpand(out.getId()).toUri()).body(out))
                .orElseThrow(() -> new GeneralException("Error in signup new user!"));
    }
}
