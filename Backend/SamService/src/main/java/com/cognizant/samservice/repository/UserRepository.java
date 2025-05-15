package com.cognizant.samservice.repository;

import com.cognizant.samservice.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // Custom query methods (if needed)
    Optional<UserEntity> findById(Long id);
}
