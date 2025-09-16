package com.example.cbs.service;

import com.example.cbs.domain.Beneficiary;
import com.example.cbs.domain.User;
import com.example.cbs.repo.BeneficiaryRepository;
import com.example.cbs.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BeneficiaryService {

    private final  BeneficiaryRepository beneficiaryRepository;
    private final UserRepository userRepository;

    public Beneficiary addBeneficiary(Long userId, Beneficiary beneficiary) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));
        beneficiary.setUser(user);
        return beneficiaryRepository.save(beneficiary);
    }

    public List<Beneficiary> getBeneficiaries(Long userId) {
        return beneficiaryRepository.findByUserId(userId);
    }
}
