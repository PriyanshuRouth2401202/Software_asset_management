package com.cognizant.samservice.controller;

import com.cognizant.samservice.model.SoftwareRequestEntity;
import com.cognizant.samservice.services.SoftwareRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/software-requests")
public class SoftwareRequestController {

    private final SoftwareRequestService softwareRequestService;

    @Autowired
    public SoftwareRequestController(SoftwareRequestService softwareRequestService) {
        this.softwareRequestService = softwareRequestService;
    }

    @PostMapping("/createRequest")
    public ResponseEntity<SoftwareRequestEntity> createSoftwareRequest(@RequestBody SoftwareRequestEntity request) {
        return ResponseEntity.ok(softwareRequestService.createSoftwareRequest(request));
    }

    @GetMapping
    public ResponseEntity<List<SoftwareRequestEntity>> getAllRequests() {
        return ResponseEntity.ok(softwareRequestService.getAllRequests());
    }

    @PostMapping("/{requestId}/respond")
    public ResponseEntity<SoftwareRequestEntity> respondToRequest(
            @PathVariable Long requestId,
            @RequestParam Long adminId,
            @RequestParam boolean accept,
            @RequestParam(required = false) String adminResponse
    ) {
        return ResponseEntity.ok(softwareRequestService.respondToRequest(requestId, adminId, accept, adminResponse));
    }
}
