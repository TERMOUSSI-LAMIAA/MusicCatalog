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
        User user = userRepository.findByLogin(username)  // Assuming you use login as the username
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Return custom UserDetails with the roles mapped to SimpleGrantedAuthority
        return new CustomUserDetails(
                user.getLogin(),
                user.getPassword(),
                user.getActive(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList())
        );
    }
}