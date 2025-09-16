package com.example.cbs.controller;

import com.example.cbs.domain.Beneficiary;
import com.example.cbs.service.BeneficiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/beneficiaries")
@RequiredArgsConstructor
public class BeneficiaryController {

    private final BeneficiaryService beneficiaryService;

    @PostMapping("/add/{userId}")
    public ResponseEntity<Beneficiary> addBeneficiary(@PathVariable Long userId,
                                                      @RequestBody Beneficiary beneficiary) {
        return ResponseEntity.ok(beneficiaryService.addBeneficiary(userId, beneficiary));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Beneficiary>> getBeneficiaries(@PathVariable Long userId) {
        return ResponseEntity.ok(beneficiaryService.getBeneficiaries(userId));
    }
}
