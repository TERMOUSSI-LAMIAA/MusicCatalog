package com.MusicCatalog.MusicCatalog.controllers;

import com.MusicCatalog.MusicCatalog.dto.requestDTO.LoginRequestDTO;
import com.MusicCatalog.MusicCatalog.dto.requestDTO.UserRequestDTO;
import com.MusicCatalog.MusicCatalog.dto.responseDTO.AuthResponseDTO;
import com.MusicCatalog.MusicCatalog.dto.responseDTO.UserResponseDTO;
import com.MusicCatalog.MusicCatalog.entities.Role;
import com.MusicCatalog.MusicCatalog.entities.User;
import com.MusicCatalog.MusicCatalog.security.JwtTokenProvider;
import com.MusicCatalog.MusicCatalog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO loginRequestDTO) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getLogin(), loginRequestDTO.getPassword()));

        return jwtTokenProvider.generateToken(authentication.getName(), authentication.getAuthorities().toString());
    }
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody @Validated UserRequestDTO userRequestDTO) {

        UserResponseDTO userResponseDTO = userService.createUser(userRequestDTO);

        String token = jwtTokenProvider.generateToken(userRequestDTO.getLogin(),
                userResponseDTO.getRoles().stream()
                        .map(Role::getName)
                        .collect(Collectors.joining(","))
        );

        AuthResponseDTO authResponse = new AuthResponseDTO(
                userResponseDTO.getId(),
                userResponseDTO.getLogin(),
                userResponseDTO.getRoles().stream()
                        .map(Role::getName)
                        .collect(Collectors.toList()),
                token
        );

        return ResponseEntity.ok(authResponse);
    }
}
