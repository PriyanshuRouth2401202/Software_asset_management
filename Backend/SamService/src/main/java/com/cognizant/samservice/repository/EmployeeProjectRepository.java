package com.cognizant.samservice.repository;

import com.cognizant.samservice.model.EmployeeProjectEntity;
import com.cognizant.samservice.model.UserEntity;
import com.cognizant.samservice.model.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List; // Import List
import java.util.Optional;

@Repository
public interface EmployeeProjectRepository extends JpaRepository<EmployeeProjectEntity, Long> {

    // Custom query to find EmployeeProjectEntity by User and Project
    Optional<EmployeeProjectEntity> findByEmployeeAndProject(UserEntity employee, ProjectEntity project);

    // New method to find EmployeeProjectEntity records by employee ID
    // Spring Data JPA will automatically generate the query based on the method name.
    // It looks for a field named 'id' within the 'employee' field of EmployeeProjectEntity.
    List<EmployeeProjectEntity> findByEmployeeId(Long employeeId);

    // If you need to find by the UserEntity object itself (as in your service)
    List<EmployeeProjectEntity> findByEmployee(UserEntity employee);
}
