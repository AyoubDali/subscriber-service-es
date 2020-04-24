package com.sofrecom.elasticsearch.repository;

import java.util.Optional;

import com.sofrecom.elasticsearch.model.ERole;
import com.sofrecom.elasticsearch.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
  Role findByName(String name);

}
