package com.cognizant.samservice.services;

import com.cognizant.samservice.model.DepartmentEntity;

import java.util.List;

public interface DepartmentService {

    DepartmentEntity createDepartment(DepartmentEntity department);

    List<DepartmentEntity> getAllDepartments();

    DepartmentEntity getDepartmentById(Long id);

    DepartmentEntity updateDepartment(Long id, DepartmentEntity department);

    void deleteDepartment(Long id);
}
