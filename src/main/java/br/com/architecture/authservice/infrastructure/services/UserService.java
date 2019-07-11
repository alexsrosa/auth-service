package br.com.architecture.authservice.infrastructure.services;

import br.com.architecture.authservice.domain.entities.UserEntity;
import br.com.architecture.authservice.infrastructure.configuration.CryptPasswordConfiguration;
import br.com.architecture.authservice.infrastructure.exceptions.EmailFoundException;
import br.com.architecture.authservice.infrastructure.repositories.UserMongoRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserMongoRepository repository;
    private CryptPasswordConfiguration cryptPasswordConfiguration;

    public UserService(UserMongoRepository repository, CryptPasswordConfiguration cryptPasswordConfiguration) {
        this.repository = repository;
        this.cryptPasswordConfiguration = cryptPasswordConfiguration;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = repository.findByEmail(username);
        if (user.isPresent()) {
            return user.get();
        }

        throw new UsernameNotFoundException("Invalid data!");
    }

    public Optional<UserEntity> findById(String id) {
        return repository.findById(id);
    }

    public Optional<UserEntity> save(UserEntity entity) {
        encodePassword(entity);
        UserEntity saved;

        try{
            saved = repository.save(entity);
        }catch (DuplicateKeyException ex){
            throw new EmailFoundException();
        }

        return Optional.ofNullable(saved);
    }

    private void encodePassword(UserEntity entity) {
        entity.setPassword(cryptPasswordConfiguration
                .bCryptPasswordEncoder()
                .encode(entity.getPassword()));
    }
}
