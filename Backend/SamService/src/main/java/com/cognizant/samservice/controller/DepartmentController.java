package com.cognizant.samservice.controller;

import com.cognizant.samservice.model.DepartmentEntity;
import com.cognizant.samservice.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
     private DepartmentRepository departmentRepository;

    // 1. Create Department
    @PostMapping("/create")
    public ResponseEntity<DepartmentEntity> createDepartment(@RequestBody DepartmentEntity department) {
        DepartmentEntity savedDepartment = departmentRepository.save(department);
        return ResponseEntity.ok(savedDepartment);
    }

    // 2. Get all Departments
    @GetMapping
    public ResponseEntity<List<DepartmentEntity>> getAllDepartments() {
        List<DepartmentEntity> departments = departmentRepository.findAll();
        return ResponseEntity.ok(departments);
    }

    // 3. Get Department by ID
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentEntity> getDepartmentById(@PathVariable Long id) {
        DepartmentEntity department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        return ResponseEntity.ok(department);
    }

    // 4. Update Department
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentEntity> updateDepartment(@PathVariable Long id, @RequestBody DepartmentEntity updatedDepartment) {
        DepartmentEntity existingDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        existingDepartment.setName(updatedDepartment.getName());
        existingDepartment.setDescription(updatedDepartment.getDescription());

        DepartmentEntity saved = departmentRepository.save(existingDepartment);
        return ResponseEntity.ok(saved);
    }

    // 5. Delete Department
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        departmentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
