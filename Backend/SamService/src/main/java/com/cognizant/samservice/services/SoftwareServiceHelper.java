package com.cognizant.samservice.services;

import com.cognizant.samservice.model.SoftwareEntity;
import java.util.List;

public interface SoftwareServiceHelper {
    SoftwareEntity registerSoftware(SoftwareEntity software);
    List<SoftwareEntity> getAllSoftware();
    SoftwareEntity getSoftwareById(Long id);
    boolean checkAvailability(Long id);
    void deleteSoftware(Long id);
    SoftwareEntity updateSoftware(Long id, SoftwareEntity updatedSoftware);
}
