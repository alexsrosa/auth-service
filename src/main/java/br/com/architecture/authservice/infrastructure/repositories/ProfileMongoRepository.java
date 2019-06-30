package br.com.architecture.authservice.infrastructure.repositories;

import br.com.architecture.authservice.domain.entities.ProfileEntity;
import br.com.architecture.authservice.domain.repositories.ProfileRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileMongoRepository extends MongoRepository<ProfileEntity, Long>, ProfileRepository {

}
