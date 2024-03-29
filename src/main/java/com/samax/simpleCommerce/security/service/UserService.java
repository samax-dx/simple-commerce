package com.samax.simpleCommerce.security.service;

import com.samax.simpleCommerce.common.excption.ScHttpException;
import com.samax.simpleCommerce.security.repository.UserRepository;
import com.samax.simpleCommerce.security.model.LoginDto;
import com.samax.simpleCommerce.security.model.RegisterDto;
import com.samax.simpleCommerce.security.model.RoleName;
import com.samax.simpleCommerce.security.model.User;
import com.samax.simpleCommerce.security.util.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;


@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private static final String MSG_USER_EXISTS = "email has already been registered";
    private static final String MSG_USER_NOT_FOUND = "user not found";
    private static final String MSG_BAD_CREDENTIAL = "invalid credentials";

    @Value("${app.admin-pass}")
    private String adminPass;

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    public String register(RegisterDto registerDto, String signupKey) {
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new ScHttpException(HttpStatus.BAD_REQUEST, MSG_USER_EXISTS);
        }

        User user = new User();
        user.setEmail(registerDto.getEmail());
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRoles(new HashSet<>(Collections.singletonList(signupKey.trim().equals(adminPass) ? RoleName.ADMIN : RoleName.USER)));
        userRepository.save(user);

        return jwtUtil.createToken(user);
    }

    public String authenticate(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new ScHttpException(HttpStatus.NOT_FOUND, MSG_USER_NOT_FOUND));

        try {
            UsernamePasswordAuthenticationToken passwordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getEmail(),
                    loginDto.getPassword(), user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(passwordAuthenticationToken));
        } catch (Exception ex) {
            throw new ScHttpException(HttpStatus.UNAUTHORIZED, MSG_BAD_CREDENTIAL);
        }

        return jwtUtil.createToken(user);
    }

}

