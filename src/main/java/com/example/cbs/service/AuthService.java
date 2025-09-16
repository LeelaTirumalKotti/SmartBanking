package com.example.cbs.service;

import com.example.cbs.config.JwtUtil;
import com.example.cbs.domain.Role;
import com.example.cbs.domain.User;
import com.example.cbs.dto.*;
import com.example.cbs.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private  final JwtUtil jwtUtil;

    public AuthResponse register(RegisterRequest request) {

        if (request.getUsername() == null || request.getUsername().isEmpty()) {
            return new AuthResponse("400", "Username is required");
        }

        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            return new AuthResponse("400", "Password is required");
        }

        if (request.getRole() == null || request.getRole().isEmpty()) {
            return new AuthResponse("400", "Role is required");
        }

        if (userRepo.findByUsername(request.getUsername()).isPresent()) {
            return new AuthResponse("409", "Username already exists");
        }

        Role role;
        try {
            role = Role.valueOf(request.getRole().toUpperCase());
        } catch (IllegalArgumentException e) {
            return new AuthResponse("400", "Invalid role specified");
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();

        userRepo.save(user);

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole().name());
        return new AuthResponse(token, user.getRole().name());
    }



    public AuthResponse login(AuthRequest req) {
        Optional<User> optionalUser = userRepo.findByUsername(req.getUsername());

        if (req.getUsername() == null || req.getUsername().isEmpty()) {
            return new AuthResponse("400", "Username is required");
        }

        if (req.getPassword() == null || req.getPassword().isEmpty()) {
            return new AuthResponse("400", "Password is required");
        }

        if (optionalUser.isEmpty()) {
            return new AuthResponse("404", "User not found");
        }

        User user = optionalUser.get();

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            return new AuthResponse("401", "Invalid password");
        }

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole().name());
        return new AuthResponse(token, user.getRole().name());
    }




}
