package com.cognizant.samservice.services;

import com.cognizant.samservice.model.UserSoftwareAllocationEntity;

import java.util.List;

public interface UserSoftwareAllocationService {
    UserSoftwareAllocationEntity createAllocation(UserSoftwareAllocationEntity allocation);

    UserSoftwareAllocationEntity getAllocationById(Long id);

    List<UserSoftwareAllocationEntity> getAllAllocations();

    void deleteAllocation(Long id);
}
