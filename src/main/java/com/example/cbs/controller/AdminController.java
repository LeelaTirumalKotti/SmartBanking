package com.example.cbs.controller;

import com.example.cbs.domain.*;
import com.example.cbs.domain.Transaction;
import com.example.cbs.service.AdminService;
import com.example.cbs.service.CustomerProfileService;
import com.example.cbs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final  CustomerProfileService customerProfileService;
    private final UserService userService;

    @PutMapping("/profiles/{id}/kyc")
    public ResponseEntity<CustomerProfile> updateKycStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        KycStatus kycStatus;
        try {
            kycStatus = KycStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

        CustomerProfile updatedProfile = customerProfileService.updateKycStatus(id, kycStatus);
        return ResponseEntity.ok(updatedProfile);
    }
    @GetMapping("/profiles")
    public ResponseEntity<List<CustomerProfile>> getAllProfiles() {
        List<CustomerProfile> profiles = customerProfileService.getAllProfiles();
        return ResponseEntity.ok(profiles);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);

    }




}

