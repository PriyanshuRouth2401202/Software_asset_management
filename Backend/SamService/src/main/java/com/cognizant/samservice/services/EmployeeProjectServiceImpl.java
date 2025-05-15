package com.cognizant.samservice.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.samservice.exception.ResourceNotFoundException;
import com.cognizant.samservice.model.EmployeeProjectEntity;
import com.cognizant.samservice.repository.EmployeeProjectRepository;

@Service
public class EmployeeProjectServiceImpl implements EmployeeProjectService {

    private final EmployeeProjectRepository employeeProjectRepository;

    public EmployeeProjectServiceImpl(EmployeeProjectRepository employeeProjectRepository) {
        this.employeeProjectRepository = employeeProjectRepository;
    }

    @Override
    @Transactional
    public EmployeeProjectEntity createEmployeeProject(EmployeeProjectEntity employeeProjectEntity) {
        return employeeProjectRepository.save(employeeProjectEntity);
    }

    @Override
    public List<EmployeeProjectEntity> getAllEmployeeProjects() {
        return employeeProjectRepository.findAll();
    }

    @Override
    public EmployeeProjectEntity getEmployeeProjectById(Long id) {
        return employeeProjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EmployeeProject not found with id: " + id));
    }

    @Override
    @Transactional
    public void deleteEmployeeProject(Long id) {
        if (!employeeProjectRepository.existsById(id)) {
            throw new ResourceNotFoundException("EmployeeProject not found with id: " + id);
        }
        employeeProjectRepository.deleteById(id);
    }
}
