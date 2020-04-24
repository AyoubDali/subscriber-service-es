package com.sofrecom.elasticsearch.repository;

import com.sofrecom.elasticsearch.model.ProfessionType;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfessionTypeRepository extends MongoRepository<ProfessionType, String> {
}
