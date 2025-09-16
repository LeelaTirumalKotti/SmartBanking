package com.example.cbs.service;

import com.example.cbs.domain.User;
import com.example.cbs.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
