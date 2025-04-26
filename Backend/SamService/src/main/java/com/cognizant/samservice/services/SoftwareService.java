package com.cognizant.samservice.services;

import com.cognizant.samservice.model.SoftwareEntity;
import com.cognizant.samservice.repository.SoftwareRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SoftwareService implements SoftwareServiceHelper {
    private final SoftwareRepository softwareRepository;

    public SoftwareService(SoftwareRepository softwareRepository) {
        this.softwareRepository = softwareRepository;
    }

    @Override
    public SoftwareEntity registerSoftware(SoftwareEntity software) {
        return softwareRepository.save(software);
    }

    @Override
    public List<SoftwareEntity> getAllSoftware() {
        return softwareRepository.findAll();
    }

    @Override
    public SoftwareEntity getSoftwareById(Long id) {
        return softwareRepository.findById(id).orElseThrow(() -> new RuntimeException("Software not found"));
    }

    @Override
    public boolean checkAvailability(Long id) {
        Optional<SoftwareEntity> software = softwareRepository.findById(id);
        return software.isPresent(); // Simplified availability check
    }

    @Override
    public void deleteSoftware(Long id) {
        softwareRepository.deleteById(id);
    }

    @Override
    public SoftwareEntity updateSoftware(Long id, SoftwareEntity updatedSoftware) {
        SoftwareEntity software = getSoftwareById(id);
        software.setName(updatedSoftware.getName());
        software.setVersion(updatedSoftware.getVersion());
        software.setLicenseType(updatedSoftware.getLicenseType());
        software.setVendor(updatedSoftware.getVendor());
        return softwareRepository.save(software);
    }

}

