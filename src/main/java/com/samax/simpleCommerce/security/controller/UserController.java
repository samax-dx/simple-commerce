package com.samax.simpleCommerce.security.controller;

import com.samax.simpleCommerce.security.model.LoginDto;
import com.samax.simpleCommerce.security.model.RegisterDto;
import com.samax.simpleCommerce.security.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/register")
    public String register(@RequestBody @Valid RegisterDto registerDto, @RequestHeader(name = "signup-key", defaultValue = "") String signupKey) {
        return userService.register(registerDto, signupKey);
    }

    @PostMapping("/login")
    public String login(@RequestBody @Valid LoginDto loginDto) {
        return userService.authenticate(loginDto);
    }

    @GetMapping("/profile")
    public Object profile(Principal principal) {
        return principal;
    }

}
