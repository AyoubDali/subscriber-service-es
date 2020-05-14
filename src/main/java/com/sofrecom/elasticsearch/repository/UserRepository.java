package com.sofrecom.elasticsearch.repository;

import java.util.Optional;

import com.sofrecom.elasticsearch.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, String> {

  Optional<User> findByUsername(String username);

  User findBySubscriberId(String id);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}
