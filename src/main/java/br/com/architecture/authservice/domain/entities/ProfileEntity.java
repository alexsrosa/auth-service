package br.com.architecture.authservice.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode
@Document(collection = "profile")
public class ProfileEntity implements GrantedAuthority {

    private static final long serialVersionUID = 4367095754917346845L;

    @Id
    private Long id;

    @NotNull
    private String name;

    @Override
    public String getAuthority() {
        return this.name;
    }
}
