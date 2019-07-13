package br.com.architecture.authservice.infrastructure.entrypoints.dtos;

import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

import java.util.Objects;

@Getter
@Setter
public class UserOutDto {

    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String token;

    private String tokenType;

    public String getFirstName() {
        return Objects.nonNull(this.firstName) ? this.firstName : Strings.EMPTY;
    }

    public String getLastName() {
        return Objects.nonNull(this.lastName) ? this.lastName : Strings.EMPTY;
    }
}
