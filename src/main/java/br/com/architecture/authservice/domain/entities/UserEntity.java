package br.com.architecture.authservice.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Document(collection = "user")
public class UserEntity implements Serializable, UserDetails {

    private static final long serialVersionUID = 5950098469087878683L;

    @Id
    private String id;

    @NotNull
    private String name;

    @NotNull @Email
    @Indexed(unique = true, name = "Email Unique")
    private String email;

    @NotNull
    private String password;

    @DBRef
    private List<ProfileEntity> profiles = new ArrayList<>();

    private Boolean isAccountNonExpired = Boolean.TRUE;

    private Boolean isAccountNonLocked = Boolean.TRUE;

    private Boolean isCredentialsNonExpired = Boolean.TRUE;

    private Boolean isEnabled = Boolean.TRUE;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.profiles;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }
}
