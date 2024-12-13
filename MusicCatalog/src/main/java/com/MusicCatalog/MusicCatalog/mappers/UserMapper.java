package com.MusicCatalog.MusicCatalog.mappers;

import com.MusicCatalog.MusicCatalog.dto.requestDTO.SongRequestDTO;
import com.MusicCatalog.MusicCatalog.dto.requestDTO.UserRequestDTO;
import com.MusicCatalog.MusicCatalog.dto.responseDTO.SongResponseDTO;
import com.MusicCatalog.MusicCatalog.dto.responseDTO.UserResponseDTO;
import com.MusicCatalog.MusicCatalog.entities.Song;
import com.MusicCatalog.MusicCatalog.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {

    UserResponseDTO toDTO(User user);

    User toEntity(UserRequestDTO userRequestDTO);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(UserRequestDTO userRequestDTO, @MappingTarget User user);
}
