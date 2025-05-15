package com.cognizant.samservice.services;

import com.cognizant.samservice.exception.ResourceNotFoundException;
import com.cognizant.samservice.model.EmployeeProjectEntity;
import com.cognizant.samservice.model.ProjectEntity;
import com.cognizant.samservice.model.SoftwareRequestEntity;
import com.cognizant.samservice.model.UserEntity;
import com.cognizant.samservice.model.UserSoftwareAllocationEntity;
import com.cognizant.samservice.model.enums.RequestStatus;
import com.cognizant.samservice.repository.EmployeeProjectRepository; // Assuming you have this repository
import com.cognizant.samservice.repository.ProjectRepository;
import com.cognizant.samservice.repository.SoftwareRequestRepository;
import com.cognizant.samservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SoftwareRequestServiceImpl implements SoftwareRequestService {

    private final SoftwareRequestRepository requestRepository;
    private final UserSoftwareAllocationService allocationService;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository; // Keep if needed for other operations, or remove if not.
    private final EmployeeProjectRepository employeeProjectRepository; // Added repository

    @Autowired
    public SoftwareRequestServiceImpl(SoftwareRequestRepository requestRepository,
                                      UserSoftwareAllocationService allocationService,
                                      UserRepository userRepository,
                                      ProjectRepository projectRepository,
                                      EmployeeProjectRepository employeeProjectRepository) { // Injected repository
        this.requestRepository = requestRepository;
        this.allocationService = allocationService;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.employeeProjectRepository = employeeProjectRepository; // Initialize
    }

    @Override
    public SoftwareRequestEntity createSoftwareRequest(SoftwareRequestEntity request) {
        // Ensure user and software entities are attached if they are detached
        if (request.getUser() != null && request.getUser().getId() != null) {
            UserEntity user = userRepository.findById(request.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + request.getUser().getId()));
            request.setUser(user);
        } else if (request.getUser() != null) {
             // Handle case where user might be new or ID is not set - depends on your business logic
            throw new IllegalArgumentException("User ID must be provided for an existing user.");
        } else {
            throw new IllegalArgumentException("User must be provided for the software request.");
        }

        // Similarly for software, if it's an existing entity
        // if (request.getSoftware() != null && request.getSoftware().getId() != null) {
        //     SoftwareEntity software = softwareRepository.findById(request.getSoftware().getId())
        //         .orElseThrow(() -> new ResourceNotFoundException("Software not found with ID: " + request.getSoftware().getId()));
        //     request.setSoftware(software);
        // }


        request.setStatus(RequestStatus.PENDING); // Default to PENDING status
        return requestRepository.save(request);
    }

    @Override
    public List<SoftwareRequestEntity> getAllRequests() {
        return requestRepository.findAll();
    }

    @Override
    @Transactional
    public SoftwareRequestEntity respondToRequest(Long requestId, Long adminId, boolean accept, String adminResponse) {
        // Fetch the request and admin user, throwing errors if not found
        SoftwareRequestEntity request = requestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Software request not found with ID: " + requestId));

        UserEntity adminUser = userRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin user not found with ID: " + adminId));

        // Check if user has the admin role
        if (adminUser.getRole() != UserEntity.Role.ADMIN) {
            throw new IllegalArgumentException("Only admins are allowed to accept or decline requests.");
        }

        // Handle acceptance or rejection of the request
        if (accept) {
            UserEntity requestingUser = request.getUser();
            if (requestingUser == null) {
                throw new IllegalStateException("User associated with the request cannot be null.");
            }

            // Fetch the EmployeeProjectEntity for the user
            // This assumes a user is associated with at most one project for this allocation context.
            // If a user can be in multiple projects, you'll need to decide which project to use
            // or modify the logic to handle multiple projects (e.g., require projectId in the request).
            List<EmployeeProjectEntity> employeeProjects = employeeProjectRepository.findByEmployee(requestingUser);

            if (employeeProjects.isEmpty()) {
                throw new IllegalStateException("User with ID: " + requestingUser.getId() + " is not associated with any project. Cannot allocate software.");
            }

            // Assuming we take the first project found.
            // Adjust if a user can be in multiple projects and a specific one is needed.
            ProjectEntity projectForAllocation = employeeProjects.get(0).getProject();
            if (projectForAllocation == null) {
                 throw new IllegalStateException("Project associated with user " + requestingUser.getId() + " is null.");
            }


            UserSoftwareAllocationEntity allocation = new UserSoftwareAllocationEntity();
            allocation.setUser(requestingUser);
            allocation.setSoftware(request.getSoftware());
            allocation.setProject(projectForAllocation); // Set the fetched project

            allocationService.createAllocation(allocation);

            request.setStatus(RequestStatus.ACCEPTED);
            request.setAdminResponse(adminResponse != null ? adminResponse : "Request accepted successfully.");
        } else {
            request.setStatus(RequestStatus.DECLINED);
            request.setAdminResponse(adminResponse != null ? adminResponse : "Request declined.");
        }
        return requestRepository.save(request);
    }
}
