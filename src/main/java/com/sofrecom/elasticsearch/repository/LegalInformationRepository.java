package com.sofrecom.elasticsearch.repository;

import com.sofrecom.elasticsearch.model.Address;
import com.sofrecom.elasticsearch.model.LegalInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LegalInformationRepository extends JpaRepository<LegalInformation, Integer> {

    LegalInformation findById(int id);
}
