package com.example.cbs.config;

import com.example.cbs.domain.User;
import com.example.cbs.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
@Service
@RequiredArgsConstructor
public class CustomerUserServiceDetails implements  UserDetailsService {


        private final UserRepository userRepository;

//        @Autowired
//        public CustomUserDetailsService(UserRepository userRepository) {
//            this.userRepository = userRepository;
//        }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_"+user.getRole().name()))
            );
        }


}
