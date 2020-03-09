package com.sofrecom.elasticsearch.repository;

import com.sofrecom.elasticsearch.model.OpeningTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpeningTimeRepository extends JpaRepository<OpeningTime, Integer> {
}
