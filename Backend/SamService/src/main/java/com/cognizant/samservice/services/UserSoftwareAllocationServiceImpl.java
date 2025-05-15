package com.cognizant.samservice.services;

import com.cognizant.samservice.exception.AllocationNotFoundException;
import com.cognizant.samservice.model.SoftwareEntity;
import com.cognizant.samservice.model.UserSoftwareAllocationEntity;
import com.cognizant.samservice.repository.SoftwareRepository;
import com.cognizant.samservice.repository.UserRepository;
import com.cognizant.samservice.repository.UserSoftwareAllocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserSoftwareAllocationServiceImpl implements UserSoftwareAllocationService {

    private final UserSoftwareAllocationRepository allocationRepository;
    private final UserRepository userRepository;
    private final SoftwareRepository softwareRepository;

    @Autowired
    public UserSoftwareAllocationServiceImpl(UserSoftwareAllocationRepository allocationRepository,
                                             UserRepository userRepository,
                                             SoftwareRepository softwareRepository) {
        this.allocationRepository = allocationRepository;
        this.userRepository = userRepository;
        this.softwareRepository = softwareRepository;
    }

    @Override
    @Transactional
    public UserSoftwareAllocationEntity createAllocation(UserSoftwareAllocationEntity allocation) {
        SoftwareEntity software = allocation.getSoftware();

        if (software != null) {
            SoftwareEntity existingSoftware = softwareRepository.findById(software.getId())
                    .orElseThrow(() -> new RuntimeException("Software not found with id: " + software.getId()));

            // Increment active user count
            existingSoftware.setActiveUser(existingSoftware.getActiveUser() + 1);
            softwareRepository.save(existingSoftware);

            // 🔧 Set the managed software entity back into the allocation
            allocation.setSoftware(existingSoftware);
        }

        // You should also do the same for user and project if they are passed with only IDs
        allocation.setUser(userRepository.findById(allocation.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found")));

        allocation.setProject(allocation.getProject()); // Optional: fetch from DB if needed

        return allocationRepository.save(allocation);
    }

    @Override
    public UserSoftwareAllocationEntity getAllocationById(Long id) {
        return allocationRepository.findById(id)
                .orElseThrow(() -> new AllocationNotFoundException("Allocation not found with id " + id));
    }

    @Override
    public List<UserSoftwareAllocationEntity> getAllAllocations() {
        return allocationRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteAllocation(Long id) {
        if (!allocationRepository.existsById(id)) {
            throw new AllocationNotFoundException("Allocation not found with id " + id);
        }
        allocationRepository.deleteById(id);
    }
}
