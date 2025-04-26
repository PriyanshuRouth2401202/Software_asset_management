package com.cognizant.samservice.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;


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
    private List<UserEntity> employees; // Employees belonging to the department

    @OneToMany(mappedBy = "department")
    private List<ProjectEntity> projects; // Projects managed by the department
}
