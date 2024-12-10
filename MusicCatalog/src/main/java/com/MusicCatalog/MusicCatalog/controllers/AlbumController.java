package com.MusicCatalog.MusicCatalog.controllers;

import com.MusicCatalog.MusicCatalog.dto.requestDTO.AlbumRequestDTO;
import com.MusicCatalog.MusicCatalog.dto.responseDTO.AlbumResponseDTO;
import com.MusicCatalog.MusicCatalog.entities.Album;
import com.MusicCatalog.MusicCatalog.services.AlbumService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/categories")
@Validated
public class AlbumController {
    private final AlbumService albumService;

    @GetMapping
    public ResponseEntity<Page<AlbumResponseDTO>> getAllAlbums(Pageable pageable) {
        Page<AlbumResponseDTO> albums=albumService.getAllAlbums(pageable);
        return ResponseEntity.ok(albums);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumResponseDTO> getAlbumById(@PathVariable Long id) {
      AlbumResponseDTO album= albumService.getAlbumById(id);
      return ResponseEntity.ok(album);
    }

    @PostMapping
    public ResponseEntity<AlbumResponseDTO> createAlbum(@RequestBody AlbumRequestDTO albumRequestDTO) {
        AlbumResponseDTO album=  albumService.createAlbum(albumRequestDTO);
        return  ResponseEntity.ok(album);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlbumResponseDTO> updateAlbum(@PathVariable Long id, @RequestBody AlbumRequestDTO albumRequestDTO) {
        AlbumResponseDTO album=albumService.updateAlbum(id, albumRequestDTO);
        return ResponseEntity.ok(album);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long id) {
        albumService.deleteAlbum(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/title")
    public ResponseEntity<Page<AlbumResponseDTO>> searchAlbumsByTitle(@RequestParam String title, Pageable pageable) {
        Page<AlbumResponseDTO> albums= albumService.searchAlbumsByTitle(title, pageable);
        return ResponseEntity.ok(albums);

    }

    @GetMapping("/search/artist")
    public ResponseEntity<List<AlbumResponseDTO>> searchAlbumsByArtist(@RequestParam String artist) {
        List<AlbumResponseDTO> albums= albumService.searchAlbumsByArtist(artist);
        return ResponseEntity.ok(albums);
    }

    @GetMapping("/filter/year")
    public ResponseEntity<Page<AlbumResponseDTO>> filterAlbumsByYear(@RequestParam int year, Pageable pageable) {
        Page<AlbumResponseDTO> albums=   albumService.filterAlbumsByYear(year, pageable);
        return ResponseEntity.ok(albums);
    }
}
