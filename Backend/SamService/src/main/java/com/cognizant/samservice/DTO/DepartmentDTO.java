package com.cognizant.samservice.DTO;

import lombok.Data;

import java.util.List;

@Data
public class DepartmentDTO {
    private Long id;
    private String name;
    private String description;

    // Optional: Include only if you want to expose related data
    private List<Long> employeeIds;
    private List<Long> projectIds;
}
