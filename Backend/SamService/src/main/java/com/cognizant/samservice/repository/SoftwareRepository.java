/**
 * 
 */
package com.cognizant.samservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.samservice.model.SoftwareEntity;

/**
 * 
 */
@Repository
public interface SoftwareRepository extends JpaRepository<SoftwareEntity, Long>{

}
