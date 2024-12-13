package com.MusicCatalog.MusicCatalog.mappers;

import com.MusicCatalog.MusicCatalog.dto.requestDTO.AlbumRequestDTO;
import com.MusicCatalog.MusicCatalog.dto.requestDTO.SongRequestDTO;
import com.MusicCatalog.MusicCatalog.dto.responseDTO.AlbumResponseDTO;
import com.MusicCatalog.MusicCatalog.dto.responseDTO.SongResponseDTO;
import com.MusicCatalog.MusicCatalog.entities.Album;
import com.MusicCatalog.MusicCatalog.entities.Song;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface SongMapper {

    @Mapping(source = "album.id", target = "albumId")
    SongResponseDTO toDTO(Song song);

    @Mapping(source = "albumId", target = "album.id")
    Song toEntity(SongRequestDTO songRequestDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "albumId", target = "album.id")
    void updateEntityFromDTO(SongRequestDTO songRequestDTO, @MappingTarget Song song);
}
