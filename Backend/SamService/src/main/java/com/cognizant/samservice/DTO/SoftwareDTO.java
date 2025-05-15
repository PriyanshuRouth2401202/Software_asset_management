package com.cognizant.samservice.DTO;

import lombok.Data;

@Data
public class SoftwareDTO {
    private Long id;
    private String name;
    private String version;
    private String licenseType;
    private String vendor;
    private Integer activeUser;
    private boolean isActive;
    private Long projectId;
}
