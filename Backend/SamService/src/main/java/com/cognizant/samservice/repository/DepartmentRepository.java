package com.cognizant.samservice.repository;

import com.cognizant.samservice.model.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {

    // Add this method to check for duplicates
    boolean existsByNameIgnoreCase(String name);

    // Optional: useful for update checks
    Optional<DepartmentEntity> findByNameIgnoreCase(String name);
}
