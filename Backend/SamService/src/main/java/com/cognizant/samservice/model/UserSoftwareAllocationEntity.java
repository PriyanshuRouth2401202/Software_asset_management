package com.cognizant.samservice.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;



@Entity
@Data
@Table(name = "user_software_allocation")
public class UserSoftwareAllocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectEntity project;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "software_id", nullable = false)
    private SoftwareEntity software;

    private LocalDate allocationDate;
}
