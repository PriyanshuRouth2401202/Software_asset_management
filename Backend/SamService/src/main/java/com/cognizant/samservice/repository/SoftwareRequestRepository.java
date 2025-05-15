package com.cognizant.samservice.repository;

import com.cognizant.samservice.model.SoftwareRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoftwareRequestRepository extends JpaRepository<SoftwareRequestEntity, Long> {
}
