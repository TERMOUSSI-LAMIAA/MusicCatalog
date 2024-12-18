package com.MusicCatalog.MusicCatalog.services;

import com.MusicCatalog.MusicCatalog.dto.requestDTO.UserRequestDTO;
import com.MusicCatalog.MusicCatalog.dto.responseDTO.UserResponseDTO;
import com.MusicCatalog.MusicCatalog.entities.Role;
import com.MusicCatalog.MusicCatalog.entities.User;
import com.MusicCatalog.MusicCatalog.exceptions.UserException;
import com.MusicCatalog.MusicCatalog.mappers.UserMapper;
import com.MusicCatalog.MusicCatalog.repositories.RoleRepository;
import com.MusicCatalog.MusicCatalog.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class UserService {
//    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @PostConstruct
    public void createDefaultUser() {

        Role userRole = roleRepository.findByName("USER").orElseGet(() -> roleRepository.save(new Role("USER")));
        Role adminRole = roleRepository.findByName("ADMIN").orElseGet(() -> roleRepository.save(new Role("ADMIN")));

        if (!userRepository.existsByLogin("user123")) {
            User user = new User();
            user.setLogin("user123");
            user.setPassword(passwordEncoder.encode("password123"));
            user.setActive(true);
            user.setRoles(Arrays.asList(userRole));

            userRepository.save(user);
        }

        if (!userRepository.existsByLogin("admin")) {
            User admin = new User();
            admin.setLogin("admin");
            admin.setPassword(passwordEncoder.encode("adminpass"));
            admin.setActive(true);
            admin.setRoles(Arrays.asList(adminRole));

            userRepository.save(admin); 
        }
    }
    public Page<UserResponseDTO> getAllUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(userMapper::toDTO);
    }

    public UserResponseDTO getUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserException(id));
        return userMapper.toDTO(user);
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User user = userMapper.toEntity(userRequestDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        List<Role> roles = userRequestDTO.getRoles().stream()
                .map(role-> roleRepository.findByName(role.getName())
                        .orElseThrow(() -> new IllegalArgumentException("Role not found: " + role)))
                .collect(Collectors.toList());

        if (roles.isEmpty()) {
            Role defaultRole = roleRepository.findByName("USER")
                    .orElseThrow(() -> new IllegalArgumentException("Default 'USER' role not found"));
            roles.add(defaultRole);
        }
        user.setRoles(roles);
        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }

    public UserResponseDTO updateUser(String id, UserRequestDTO userRequestDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserException(id));
        userMapper.updateEntityFromDTO(userRequestDTO, user);
        User updatedUser = userRepository.save(user);
        return userMapper.toDTO(updatedUser);
    }

    public void deleteUser(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserException(id));
        userRepository.delete(user);
    }
}
