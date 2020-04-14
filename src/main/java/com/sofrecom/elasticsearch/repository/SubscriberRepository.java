package com.sofrecom.elasticsearch.repository;

import com.sofrecom.elasticsearch.model.Subscriber;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubscriberRepository extends MongoRepository<Subscriber, String> {
    Subscriber findById(int id);
}
