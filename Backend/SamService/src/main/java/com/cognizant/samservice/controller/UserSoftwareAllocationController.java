package com.cognizant.samservice.controller;

import com.cognizant.samservice.model.UserSoftwareAllocationEntity;
import com.cognizant.samservice.services.UserSoftwareAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/software/allocate")
public class UserSoftwareAllocationController {

    @Autowired
    private UserSoftwareAllocationService allocationService;

    // Create a new allocation
    @PostMapping("/create")
    public ResponseEntity<UserSoftwareAllocationEntity> createAllocation(@RequestBody UserSoftwareAllocationEntity allocation) {
        UserSoftwareAllocationEntity created = allocationService.createAllocation(allocation);
        return ResponseEntity.ok(created);
    }

    // Get allocation by id
    @GetMapping("/searchAllocation")
    public ResponseEntity<UserSoftwareAllocationEntity> getAllocationById(@RequestParam Long id) {
        UserSoftwareAllocationEntity allocation = allocationService.getAllocationById(id);
        if (allocation != null) {
            return ResponseEntity.ok(allocation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get all allocations
    @GetMapping("/getall")
    public ResponseEntity<List<UserSoftwareAllocationEntity>> getAllAllocations() {
        List<UserSoftwareAllocationEntity> allocations = allocationService.getAllAllocations();
        return ResponseEntity.ok(allocations);
    }

    // Delete an allocation
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAllocation(@PathVariable Long id) {
        allocationService.deleteAllocation(id);
        return ResponseEntity.noContent().build();
    }
}
