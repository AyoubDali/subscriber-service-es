package com.sofrecom.elasticsearch.repository;

import com.sofrecom.elasticsearch.model.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Integer> {

    Subscriber findById(int id);
}
