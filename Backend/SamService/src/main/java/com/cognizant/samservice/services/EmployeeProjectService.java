package com.cognizant.samservice.services;

import com.cognizant.samservice.model.EmployeeProjectEntity;

import java.util.List;

public interface EmployeeProjectService {
    EmployeeProjectEntity createEmployeeProject(EmployeeProjectEntity employeeProjectEntity);
    List<EmployeeProjectEntity> getAllEmployeeProjects();
    EmployeeProjectEntity getEmployeeProjectById(Long id);
    void deleteEmployeeProject(Long id);
}
