package com.MusicCatalog.MusicCatalog.mappers;


import com.MusicCatalog.MusicCatalog.dto.requestDTO.AlbumRequestDTO;
import com.MusicCatalog.MusicCatalog.dto.responseDTO.AlbumResponseDTO;
import com.MusicCatalog.MusicCatalog.entities.Album;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface AlbumMapper {
    AlbumResponseDTO toResponseDTO(Album album);

    Album toEntity(AlbumRequestDTO albumRequestDTO);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(AlbumRequestDTO albumRequestDTO, @MappingTarget Album album);
}
