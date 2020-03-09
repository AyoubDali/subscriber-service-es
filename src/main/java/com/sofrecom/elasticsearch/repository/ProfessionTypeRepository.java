package com.sofrecom.elasticsearch.repository;

import com.sofrecom.elasticsearch.model.ProfessionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionTypeRepository extends JpaRepository<ProfessionType, Integer> {
}
