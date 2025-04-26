package com.cognizant.samservice.model;
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
    private UserEntity employee; // Reference to UserEntity (Employee)

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectEntity project; // Reference to ProjectEntity

    private String roleInProject; // e.g., Developer, Tester, Manager
}
