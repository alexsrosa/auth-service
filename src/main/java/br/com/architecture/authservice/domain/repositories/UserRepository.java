package br.com.architecture.authservice.domain.repositories;

import br.com.architecture.authservice.domain.entities.UserEntity;

import java.util.Optional;

public interface UserRepository {

    Optional<UserEntity> findByEmail(String email);
}
