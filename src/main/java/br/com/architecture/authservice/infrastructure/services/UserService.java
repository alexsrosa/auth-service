package br.com.architecture.authservice.infrastructure.services;

import br.com.architecture.authservice.domain.entities.UserEntity;
import br.com.architecture.authservice.infrastructure.repositories.UserMongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserMongoRepository repository;

    public UserService(UserMongoRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = repository.findByEmail(username);
        if (user.isPresent()) {
            return user.get();
        }

        throw new UsernameNotFoundException("Invalid data!");
    }

    public Optional<UserEntity> findById(String id){
        return repository.findById(id);
    }
}
