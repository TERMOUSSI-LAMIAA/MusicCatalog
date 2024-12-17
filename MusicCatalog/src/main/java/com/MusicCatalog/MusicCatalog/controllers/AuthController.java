package com.MusicCatalog.MusicCatalog.controllers;

import com.MusicCatalog.MusicCatalog.dto.requestDTO.LoginRequestDTO;
import com.MusicCatalog.MusicCatalog.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO loginRequestDTO) {
        // Authentication logic using the provided login and password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getLogin(), loginRequestDTO.getPassword()));

        // Generate the JWT token if authentication is successful
        return jwtTokenProvider.generateToken(authentication.getName(), authentication.getAuthorities().toString());
    }
}
