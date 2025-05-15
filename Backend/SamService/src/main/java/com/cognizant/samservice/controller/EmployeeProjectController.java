package com.cognizant.samservice.controller;

import com.cognizant.samservice.model.EmployeeProjectEntity;
import com.cognizant.samservice.services.EmployeeProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class EmployeeProjectController {

    private final EmployeeProjectService employeeProjectService;

    @Autowired
    public EmployeeProjectController(EmployeeProjectService employeeProjectService) {
        this.employeeProjectService = employeeProjectService;
    }

    @PostMapping("/create/assign")
    public EmployeeProjectEntity createEmployeeProject(@RequestBody EmployeeProjectEntity employeeProjectEntity) {
        return employeeProjectService.createEmployeeProject(employeeProjectEntity);
    }

    @GetMapping("/showEmployeeProject")
    public List<EmployeeProjectEntity> getAllEmployeeProjects() {
        return employeeProjectService.getAllEmployeeProjects();
    }

    @GetMapping("/{id}")
    public EmployeeProjectEntity getEmployeeProjectById(@PathVariable Long id) {
        return employeeProjectService.getEmployeeProjectById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeProject(@PathVariable Long id) {
        employeeProjectService.deleteEmployeeProject(id);
    }
}
