package com.example.cbs.repo;

import com.example.cbs.domain.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
//    List<Beneficiary> findByOwnerId(Long ownerId);

    List<Beneficiary> findByUserId(Long userId);
}
