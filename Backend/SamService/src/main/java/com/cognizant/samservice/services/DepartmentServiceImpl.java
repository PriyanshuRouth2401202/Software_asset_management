package com.cognizant.samservice.services;

import com.cognizant.samservice.DTO.DepartmentDTO;
import com.cognizant.samservice.exception.DuplicateEntryException;
import com.cognizant.samservice.model.DepartmentEntity;
import com.cognizant.samservice.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentEntity createDepartment(DepartmentEntity department) {
        if (departmentRepository.existsByNameIgnoreCase(department.getName())) {
            throw new DuplicateEntryException("Department with name '" + department.getName() + "' already exists.");
        }
        return departmentRepository.save(department);
    }

    @Override
    public List<DepartmentEntity> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public DepartmentEntity getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
    }

    @Override
    public DepartmentEntity updateDepartment(Long id, DepartmentEntity updatedDepartment) {
        DepartmentEntity existingDepartment = getDepartmentById(id);

        // Check if the new name is already used by another department
        Optional<DepartmentEntity> duplicate = departmentRepository.findByNameIgnoreCase(updatedDepartment.getName());
        if (duplicate.isPresent() && !duplicate.get().getId().equals(id)) {
            throw new DuplicateEntryException("Another department with name '" + updatedDepartment.getName() + "' already exists.");
        }

        existingDepartment.setName(updatedDepartment.getName());
        existingDepartment.setDescription(updatedDepartment.getDescription());
        return departmentRepository.save(existingDepartment);
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}
