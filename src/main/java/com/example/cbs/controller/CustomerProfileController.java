package com.example.cbs.controller;

import com.example.cbs.domain.CustomerProfile;
import com.example.cbs.domain.CustomerProfile;
import com.example.cbs.domain.User;
import com.example.cbs.responses.ServiceResponse;
import com.example.cbs.service.CustomerProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RestController
@RequestMapping("/api/customer/profile")
@RequiredArgsConstructor
public class CustomerProfileController  implements WebMvcConfigurer {

    private final CustomerProfileService customerProfileService;

//    @PostMapping("/create")
//    public ResponseEntity<CustomerProfile> createProfile(@RequestBody CustomerProfile profile) {
//        return ResponseEntity.ok(customerProfileService.saveProfile(profile));
//    }

    @PostMapping("/create")
    public ResponseEntity<?> saveProfile(@RequestBody CustomerProfile profile) {
        ServiceResponse<CustomerProfile> response = customerProfileService.saveProfile(profile);

        if ("FAILURE".equals(response.getStatus())) {
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(response);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<CustomerProfile> getProfile(@PathVariable Long userId) {
        return customerProfileService.getProfileByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateProfile(@PathVariable Long userId,
                                              @RequestBody CustomerProfile profile) {
        return ResponseEntity.ok(customerProfileService.updateProfile(userId, profile).getUser());
    }
}
