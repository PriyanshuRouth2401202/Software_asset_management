package com.cognizant.samservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cognizant.samservice.model.SoftwareEntity;
import com.cognizant.samservice.services.SoftwareService;

import java.util.List;

@RestController
@RequestMapping("/api/software")
@CrossOrigin("*")
public class SoftwareController {
    private final SoftwareService softwareService;

    public SoftwareController(SoftwareService softwareService) {
        this.softwareService = softwareService;
    }

    // Add new software
    @PostMapping("/registerSoftware")
    public ResponseEntity<SoftwareEntity> registerSoftware(@RequestBody SoftwareEntity software) {
        SoftwareEntity savedSoftware = softwareService.registerSoftware(software);
        return ResponseEntity.ok(savedSoftware);
    }

    // Get all software
    @GetMapping("/getAll")
    public ResponseEntity<List<SoftwareEntity>> getAllSoftware() {
        List<SoftwareEntity> softwareList = softwareService.getAllSoftware();
        return ResponseEntity.ok(softwareList);
    }

    // Get a specific software by ID
    @GetMapping("/getById")
    public ResponseEntity<SoftwareEntity> getSoftwareById(@RequestParam Long id) {
        SoftwareEntity software = softwareService.getSoftwareById(id);
        return ResponseEntity.ok(software);
    }

    // Check software availability or status
    @GetMapping("/checkAvailability")
    public ResponseEntity<String> checkSoftwareAvailability(@RequestParam Long id) {
        boolean isAvailable = softwareService.checkAvailability(id);
        if (isAvailable) {
            return ResponseEntity.ok("Software is available.");
        } else {
            return ResponseEntity.ok("Software is not available.");
        }
    }

    // Delete a software
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteSoftware(@RequestParam Long id) {
        softwareService.deleteSoftware(id);
        return ResponseEntity.ok("Software deleted successfully.");
    }

    // Update software details
    @PutMapping("/update")
    public ResponseEntity<SoftwareEntity> updateSoftware(@RequestParam Long id, @RequestBody SoftwareEntity updatedSoftware) {
        SoftwareEntity software = softwareService.updateSoftware(id, updatedSoftware);
        return ResponseEntity.ok(software);
    }

    // Welcome home page
    @GetMapping("/home")
    public ResponseEntity<String> home() {
        String htmlResponse = "<html><body><h1>Welcome</h1></body></html>";
        return ResponseEntity.ok(htmlResponse);
    }
}
