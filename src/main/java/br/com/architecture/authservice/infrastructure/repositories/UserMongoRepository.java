package br.com.architecture.authservice.infrastructure.repositories;

import br.com.architecture.authservice.domain.entities.UserEntity;
import br.com.architecture.authservice.domain.repositories.UserRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMongoRepository extends MongoRepository<UserEntity, String>, UserRepository {

}
