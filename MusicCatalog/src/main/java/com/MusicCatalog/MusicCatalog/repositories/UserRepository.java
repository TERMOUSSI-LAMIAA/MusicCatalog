package com.MusicCatalog.MusicCatalog.repositories;

import com.MusicCatalog.MusicCatalog.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
