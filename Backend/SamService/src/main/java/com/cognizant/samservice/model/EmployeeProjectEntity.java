package com.cognizant.samservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "employee_project")
public class EmployeeProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    @JsonIgnoreProperties({"department", "password", "email", "createdAt", "updatedAt", "createdBy", "updatedBy", "employeeProjects"})
    private UserEntity employee; // Reference to UserEntity (Employee)

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    @JsonIgnoreProperties({"department", "employeeProjects", "softwareAllocations"})
    private ProjectEntity project; // Reference to ProjectEntity

    private String roleInProject; // e.g., Developer, Tester, Manager
}
