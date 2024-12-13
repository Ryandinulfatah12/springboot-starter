package com.spring_boilerplate.Boilerplate.service;

import com.spring_boilerplate.Boilerplate.dto.UserDTO;
import com.spring_boilerplate.Boilerplate.entity.User;
import com.spring_boilerplate.Boilerplate.exception.CustomException;
import com.spring_boilerplate.Boilerplate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User createUser(UserDTO request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new CustomException(HttpStatus.BAD_REQUEST.value(), "Email already in use", null);
        }
        String hashedPassword = bCryptPasswordEncoder.encode(request.getPassword());
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPassword());
        user.setPassword(hashedPassword);

        return userRepository.save(user);
    }
}
