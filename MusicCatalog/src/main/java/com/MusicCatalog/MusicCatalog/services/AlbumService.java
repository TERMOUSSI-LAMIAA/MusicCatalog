package com.MusicCatalog.MusicCatalog.services;

import com.MusicCatalog.MusicCatalog.dto.requestDTO.AlbumRequestDTO;
import com.MusicCatalog.MusicCatalog.dto.responseDTO.AlbumResponseDTO;
import com.MusicCatalog.MusicCatalog.entities.Album;
import com.MusicCatalog.MusicCatalog.exceptions.AlbumException;
import com.MusicCatalog.MusicCatalog.mappers.AlbumMapper;
import com.MusicCatalog.MusicCatalog.repositories.AlbumRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final AlbumMapper albumMapper;

    public Page<AlbumResponseDTO> getAllAlbums(Pageable pageable) {
       Page<Album> albums = albumRepository.findAll(pageable);
        return albums.map(albumMapper::toDTO);
    }

    public AlbumResponseDTO getAlbumById(String id) {
        Album album=albumRepository.findById(id).orElseThrow(()->new AlbumException(id));
        return albumMapper.toDTO(album);
    }

    public AlbumResponseDTO createAlbum(AlbumRequestDTO albumRequestDTO) {
        Album album = albumMapper.toEntity(albumRequestDTO);
        Album savedAlbum = albumRepository.save(album);
        return albumMapper.toDTO(savedAlbum);
    }

    public AlbumResponseDTO updateAlbum(String id, AlbumRequestDTO albumRequestDTO) {
        Album album=albumRepository.findById(id).orElseThrow(()->new AlbumException(id));
        albumMapper.updateEntityFromDTO(albumRequestDTO,album);
        Album updatedAlbum = albumRepository.save(album);
        return albumMapper.toDTO(updatedAlbum);
    }

    public void deleteAlbum(String id) {
        Album album=albumRepository.findById(id).orElseThrow(()->new AlbumException(id));
        albumRepository.delete(album);
    }

    public Page<AlbumResponseDTO> searchAlbumsByTitle(String title, Pageable pageable) {
        Page<Album> albumsPage = albumRepository.findByTitleContaining(title, pageable);
        return albumsPage.map(albumMapper::toDTO);
    }

    public List<AlbumResponseDTO> searchAlbumsByArtist(String artist) {
        List<Album> albums = albumRepository.findByArtistContaining(artist);
        return albums.stream().map(albumMapper::toDTO).collect(Collectors.toList());
    }

    public Page<AlbumResponseDTO> filterAlbumsByYear(int year,Pageable pageable) {
        Page<Album> albumsPage = albumRepository.findByYear(year, pageable);
        return albumsPage.map(albumMapper::toDTO);
    }
}
