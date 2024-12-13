package com.spring_boilerplate.Boilerplate.controller.user;

import com.spring_boilerplate.Boilerplate.dto.UserDTO;
import com.spring_boilerplate.Boilerplate.entity.User;
import com.spring_boilerplate.Boilerplate.service.AuthenticationService;
import com.spring_boilerplate.Boilerplate.service.UserService;
import com.spring_boilerplate.Boilerplate.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public ApiResponse<User> createUser(@Valid @RequestBody UserDTO userDTO) {
        User user = authenticationService.signup(userDTO);
        return new ApiResponse<>(201, true, "User created successfully", user, null);
    }
}
