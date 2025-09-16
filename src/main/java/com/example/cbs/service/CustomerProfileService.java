package com.example.cbs.service;

import com.example.cbs.domain.CustomerProfile;
import com.example.cbs.domain.KycStatus;
import com.example.cbs.domain.User;
import com.example.cbs.repo.CustomerProfileRepository;
import com.example.cbs.repo.UserRepository;
import com.example.cbs.responses.ServiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CustomerProfileService {

    private final CustomerProfileRepository customerProfileRepository;
    private final UserRepository userRepository;


    public ServiceResponse<CustomerProfile> saveProfile(CustomerProfile profile) {
        Optional<User> userOpt = userRepository.findById(profile.getUser().getId());

        if (userOpt.isEmpty()) {
            return new ServiceResponse<>("FAILURE", "User not found with ID: " + profile.getUser().getId(), null);
        }

        profile.setUser(userOpt.get());
        CustomerProfile savedProfile = customerProfileRepository.save(profile);
        return new ServiceResponse<>("SUCCESS", "Profile saved successfully", savedProfile);
    }


    public Optional<CustomerProfile> getProfileByUserId(Long userId) {
        return customerProfileRepository.findByUserId(userId);
    }

    public CustomerProfile updateProfile(Long userId, CustomerProfile updatedProfile) {
        return customerProfileRepository.findByUserId(userId)
                .map(existing -> {
                    existing.setFullName(updatedProfile.getFullName());
                    existing.setPhone(updatedProfile.getPhone());
                    existing.setEmail(updatedProfile.getEmail());
                    existing.setDateOfBirth(updatedProfile.getDateOfBirth());
                    existing.setAddress(updatedProfile.getAddress());
                    existing.setCity(updatedProfile.getCity());
                    existing.setState(updatedProfile.getState());
                    existing.setCountry(updatedProfile.getCountry());
                    existing.setPincode(updatedProfile.getPincode());
                    return customerProfileRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Profile not found for userId " + userId));
    }

    public CustomerProfile updateKycStatus(Long id, KycStatus kycStatus) {
        return customerProfileRepository.findById(id)
                .map(profile -> {
                    profile.setKycStatus(kycStatus);
                    return customerProfileRepository.save(profile);
                })
                .orElseThrow(() -> new RuntimeException("Customer profile not found with id " + id));

    }

    public List<CustomerProfile> getAllProfiles() {
        return customerProfileRepository.findAll();
    }

}
