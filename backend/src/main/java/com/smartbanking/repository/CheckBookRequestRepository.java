package com.smartbanking.repository;

import com.smartbanking.entity.CheckBookRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckBookRequestRepository extends JpaRepository<CheckBookRequest,Long> {
}
