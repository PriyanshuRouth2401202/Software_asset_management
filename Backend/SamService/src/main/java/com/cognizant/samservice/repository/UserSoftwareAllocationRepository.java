package com.cognizant.samservice.repository;

import com.cognizant.samservice.model.UserSoftwareAllocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSoftwareAllocationRepository extends JpaRepository<UserSoftwareAllocationEntity, Long> {
}
