package com.example.cbs.repo;

import com.example.cbs.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
//    Optional<User> finalALlUser();

    boolean existsByUsername(String username);
}
