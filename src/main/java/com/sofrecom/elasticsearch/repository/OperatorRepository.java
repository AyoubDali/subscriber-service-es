package com.sofrecom.elasticsearch.repository;

import com.sofrecom.elasticsearch.model.Operator;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OperatorRepository extends MongoRepository<Operator, String> {

}
