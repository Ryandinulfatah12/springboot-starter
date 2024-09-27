package com.spring_boilerplate.Boilerplate.service;

import com.spring_boilerplate.Boilerplate.dto.UserDTO;
import com.spring_boilerplate.Boilerplate.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setId(1L);
        return user;
    }
}
