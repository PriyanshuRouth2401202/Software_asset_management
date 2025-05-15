package com.cognizant.samservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "department")
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Department name (e.g., IT, HR, Finance)
    private String description; // Optional description of the department

    @OneToMany(mappedBy = "department")
    @JsonIgnoreProperties({"department", "password", "email", "createdAt", "updatedAt", "createdBy", "updatedBy", "employeeProjects"})
    private List<UserEntity> employees; // Employees belonging to the department

    @OneToMany(mappedBy = "department")
    @JsonIgnoreProperties({"department", "employeeProjects", "softwareAllocations"})
    private List<ProjectEntity> projects; // Projects managed by the department
}
