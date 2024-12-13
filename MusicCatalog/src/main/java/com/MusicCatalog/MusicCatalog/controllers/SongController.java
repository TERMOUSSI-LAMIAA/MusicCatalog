package com.MusicCatalog.MusicCatalog.controllers;

import com.MusicCatalog.MusicCatalog.dto.requestDTO.SongRequestDTO;
import com.MusicCatalog.MusicCatalog.dto.responseDTO.SongResponseDTO;
import com.MusicCatalog.MusicCatalog.services.SongService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Validated
public class SongController {

    private final SongService songService;

    @GetMapping("/songs")
    public ResponseEntity<Page<SongResponseDTO>> getAllSongs(Pageable pageable) {
        Page<SongResponseDTO> songs = songService.getAllSongs(pageable);
        return ResponseEntity.ok(songs);
    }

    @GetMapping("/songs/{id}")
    public ResponseEntity<SongResponseDTO> getSongById(@PathVariable String id) {
        SongResponseDTO song = songService.getSongById(id);
        return ResponseEntity.ok(song);
    }

    @PostMapping("/songs")
    public ResponseEntity<SongResponseDTO> createSong(@RequestBody SongRequestDTO songRequestDTO) {
        SongResponseDTO song = songService.createSong(songRequestDTO);
        return ResponseEntity.ok(song);
    }

    @PutMapping("/songs/{id}")
    public ResponseEntity<SongResponseDTO> updateSong(@PathVariable String id, @RequestBody SongRequestDTO songRequestDTO) {
        SongResponseDTO song = songService.updateSong(id, songRequestDTO);
        return ResponseEntity.ok(song);
    }

    @DeleteMapping("/songs/{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable String id) {
        songService.deleteSong(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/songs/search/title")
    public ResponseEntity<Page<SongResponseDTO>> searchSongsByTitle(@RequestParam String title, Pageable pageable) {
        Page<SongResponseDTO> songs = songService.searchSongsByTitle(title, pageable);
        return ResponseEntity.ok(songs);
    }

    @GetMapping("/songs/search/album")
    public ResponseEntity<List<SongResponseDTO>> searchSongsByAlbumId(@RequestParam String albumId) {
        List<SongResponseDTO> songs = songService.searchSongsByAlbumId(albumId);
        return ResponseEntity.ok(songs);
    }
}
