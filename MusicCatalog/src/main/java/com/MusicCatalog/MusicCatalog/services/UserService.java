package com.MusicCatalog.MusicCatalog.services;

import com.MusicCatalog.MusicCatalog.dto.requestDTO.UserRequestDTO;
import com.MusicCatalog.MusicCatalog.dto.responseDTO.UserResponseDTO;
import com.MusicCatalog.MusicCatalog.entities.User;
import com.MusicCatalog.MusicCatalog.exceptions.UserException;
import com.MusicCatalog.MusicCatalog.mappers.UserMapper;
import com.MusicCatalog.MusicCatalog.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserService {
//    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private final UserMapper userMapper;

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
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //check login uunique
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
