package com.sec.jwt.service;


import com.sec.jwt.entity.User;
import com.sec.jwt.repository.UserRepository;
import com.sec.jwt.utility.RegisterRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void registerUser(RegisterRequest request){
       Optional<User> user1 = userRepository.findByUsername(request.getUsername());
        if(user1.isPresent()){
            System.out.println("Result : "+userRepository.existByUsername(request.getUsername()));
            throw new IllegalArgumentException("Username is already in use");
        }

        User user = User.builder()
                .fullName(request.getFullName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        userRepository.save(user);
    }
}
