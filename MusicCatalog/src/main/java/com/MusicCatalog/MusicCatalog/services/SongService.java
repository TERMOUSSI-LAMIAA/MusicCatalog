package com.MusicCatalog.MusicCatalog.services;

import com.MusicCatalog.MusicCatalog.dto.requestDTO.SongRequestDTO;
import com.MusicCatalog.MusicCatalog.dto.responseDTO.SongResponseDTO;
import com.MusicCatalog.MusicCatalog.entities.Song;
import com.MusicCatalog.MusicCatalog.exceptions.SongException;
import com.MusicCatalog.MusicCatalog.mappers.SongMapper;
import com.MusicCatalog.MusicCatalog.repositories.SongRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SongService {

    private final SongRepository songRepository;
    private final SongMapper songMapper;

    public Page<SongResponseDTO> getAllSongs(Pageable pageable) {
        Page<Song> songs = songRepository.findAll(pageable);
        return songs.map(songMapper::toDTO);
    }

    public SongResponseDTO getSongById(String id) {
        Song song = songRepository.findById(id).orElseThrow(() -> new SongException(id));
        return songMapper.toDTO(song);
    }

    public SongResponseDTO createSong(SongRequestDTO songRequestDTO) {
        Song song = songMapper.toEntity(songRequestDTO);
        Song savedSong = songRepository.save(song);
        return songMapper.toDTO(savedSong);
    }

    public SongResponseDTO updateSong(String id, SongRequestDTO songRequestDTO) {
        Song song = songRepository.findById(id).orElseThrow(() -> new SongException(id));
        songMapper.updateEntityFromDTO(songRequestDTO, song);
        Song updatedSong = songRepository.save(song);
        return songMapper.toDTO(updatedSong);
    }

    public void deleteSong(String id) {
        Song song = songRepository.findById(id).orElseThrow(() -> new SongException(id));
        songRepository.delete(song);
    }

    public Page<SongResponseDTO> searchSongsByTitle(String title, Pageable pageable) {
        Page<Song> songsPage = songRepository.findByTitleContaining(title, pageable);
        return songsPage.map(songMapper::toDTO);
    }

    public List<SongResponseDTO> searchSongsByAlbumId(String albumId) {
        List<Song> songs = songRepository.findByAlbumId(albumId);
        return songs.stream().map(songMapper::toDTO).collect(Collectors.toList());
    }
}
