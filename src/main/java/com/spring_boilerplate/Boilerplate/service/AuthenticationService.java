package com.spring_boilerplate.Boilerplate.service;

import com.spring_boilerplate.Boilerplate.dto.UserDTO;
import com.spring_boilerplate.Boilerplate.dto.auth.LoginRequest;
import com.spring_boilerplate.Boilerplate.entity.User;
import com.spring_boilerplate.Boilerplate.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(UserDTO input) {
        User user = new User();
        user.setName(input.getName());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setEmail(input.getEmail());
        user.setRole("administrator");
        return userRepository.save(user);
    }

    public User authenticate(LoginRequest input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}