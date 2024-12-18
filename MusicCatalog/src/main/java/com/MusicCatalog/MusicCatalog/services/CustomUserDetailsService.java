package com.MusicCatalog.MusicCatalog.services;

import com.MusicCatalog.MusicCatalog.entities.User;
import com.MusicCatalog.MusicCatalog.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new CustomUserDetails(
                user.getLogin(),
                user.getPassword(),
                user.getActive(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" +role.getName()))
                        .collect(Collectors.toList())
        );
    }
}