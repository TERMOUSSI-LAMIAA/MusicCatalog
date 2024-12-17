package com.MusicCatalog.MusicCatalog.repositories;

import com.MusicCatalog.MusicCatalog.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByLogin(String login);
}
