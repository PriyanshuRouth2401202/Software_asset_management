package com.cognizant.samservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "project")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    @JsonIgnoreProperties({"employees", "projects"})
    private DepartmentEntity department;

    @OneToMany(mappedBy = "project")
    @JsonIgnoreProperties({"project", "user", "software"})
    private List<UserSoftwareAllocationEntity> softwareAllocations;

    @OneToMany(mappedBy = "project")
    @JsonIgnoreProperties({"project", "employee"})
    private List<EmployeeProjectEntity> employeeProjects;
}
