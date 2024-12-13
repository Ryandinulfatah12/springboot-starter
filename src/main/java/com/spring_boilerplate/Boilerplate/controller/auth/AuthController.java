package com.spring_boilerplate.Boilerplate.controller.auth;

import com.spring_boilerplate.Boilerplate.dto.UserDTO;
import com.spring_boilerplate.Boilerplate.dto.auth.LoginRequest;
import com.spring_boilerplate.Boilerplate.dto.auth.LoginResponse;
import com.spring_boilerplate.Boilerplate.entity.User;
import com.spring_boilerplate.Boilerplate.service.AuthenticationService;
import com.spring_boilerplate.Boilerplate.service.JwtService;
import com.spring_boilerplate.Boilerplate.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@Valid @RequestBody LoginRequest request) throws Exception {
        User authenticatedUser = authenticationService.authenticate(request);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse response = new LoginResponse();
        response.setToken(jwtToken);
        response.setExpiresIn(jwtService.getExpirationTime());
        return new ApiResponse<LoginResponse>(200, true, "User login successfully", response,  null);
    }

    @PostMapping("/signup")
    public ApiResponse<User> signup(@Valid @RequestBody UserDTO request) throws Exception {
        User user = authenticationService.signup(request);
        return new ApiResponse<User>(200, true, "User created successfully", user,  null);
    }
}
