package com.rateLimiter.RateLimiter.controllers;

import com.rateLimiter.RateLimiter.dto.LoginDto;
import com.rateLimiter.RateLimiter.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController

public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/setuser")
    public LoginDto createUser(@RequestBody LoginDto loginDTO) {
        return userService.createUser(loginDTO);
    }
}
