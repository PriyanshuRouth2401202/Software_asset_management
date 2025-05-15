package com.cognizant.samservice.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
@Table(name="software")
public class SoftwareEntity 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String name;
    private String version;
    private String licenseType;
    private String vendor;
    private Integer ActiveUser;
    private boolean isActive = true; // New field to track active status
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "project_id", nullable = false)
    @JsonIgnoreProperties({"department", "employeeProjects", "softwareAllocations"})
    private ProjectEntity project;  // Link to the project that uses this software


}
