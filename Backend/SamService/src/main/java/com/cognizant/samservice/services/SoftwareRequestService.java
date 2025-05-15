package com.cognizant.samservice.services;

import com.cognizant.samservice.model.SoftwareRequestEntity;

import java.util.List;

public interface SoftwareRequestService {
    SoftwareRequestEntity createSoftwareRequest(SoftwareRequestEntity request);
    List<SoftwareRequestEntity> getAllRequests();
    SoftwareRequestEntity respondToRequest(Long requestId, Long adminId, boolean accept, String adminResponse);
}
