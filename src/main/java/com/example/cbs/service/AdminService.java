package com.example.cbs.service;

import com.example.cbs.domain.CustomerProfile;
import com.example.cbs.domain.KycStatus;
import com.example.cbs.domain.Transaction;
import com.example.cbs.domain.User;
import com.example.cbs.repo.CustomerProfileRepository;
import com.example.cbs.repo.TransactionRepository;
import com.example.cbs.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final  CustomerProfileRepository customerProfileRepository;
    private final TransactionRepository transactionRepository;
    private final UserService userService;

    public CustomerProfile updateKycStatus(Long profileId, String status) {
        CustomerProfile profile = customerProfileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile not found with id " + profileId));
        profile.setKycStatus(KycStatus.valueOf(status.toUpperCase()));
        return customerProfileRepository.save(profile);
    }


        public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
