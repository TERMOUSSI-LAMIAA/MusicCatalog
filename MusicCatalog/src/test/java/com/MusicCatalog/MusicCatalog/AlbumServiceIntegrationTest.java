package com.MusicCatalog.MusicCatalog;

import com.MusicCatalog.MusicCatalog.dto.requestDTO.AlbumRequestDTO;
import com.MusicCatalog.MusicCatalog.dto.responseDTO.AlbumResponseDTO;
import com.MusicCatalog.MusicCatalog.services.AlbumService;
import org.springframework.boot.test.context.SpringBootTest;

import com.MusicCatalog.MusicCatalog.entities.Album;
import com.MusicCatalog.MusicCatalog.repositories.AlbumRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AlbumServiceIntegrationTest {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private AlbumService albumService;

    @BeforeEach
    void setUp() {
        // Clear the database before each test
        albumRepository.deleteAll();
    }

    @Test
    void testCreateAlbumIntegration() {
        // Arrange
        AlbumRequestDTO albumRequestDTO = new AlbumRequestDTO();
        albumRequestDTO.setTitle("Test Album");
        albumRequestDTO.setArtist("Test Artist");
        albumRequestDTO.setYear(2023);

        // Act
        AlbumResponseDTO resultDTO = albumService.createAlbum(albumRequestDTO);

        // Assert
        assertNotNull(resultDTO);
        assertEquals("Test Album", resultDTO.getTitle());
        assertEquals("Test Artist", resultDTO.getArtist());

        Album savedAlbum = albumRepository.findById(resultDTO.getId()).orElse(null);
        assertNotNull(savedAlbum);
        assertEquals("Test Album", savedAlbum.getTitle());
    }

    @Test
    void testGetAllAlbumsIntegration() {
        // Arrange
        Album album1 = new Album();
        album1.setTitle("Album 1");
        album1.setArtist("Artist 1");
        album1.setYear(2020);
        albumRepository.save(album1);

        Album album2 = new Album();
        album2.setTitle("Album 2");
        album2.setArtist("Artist 2");
        album2.setYear(2021);
        albumRepository.save(album2);

        PageRequest pageable = PageRequest.of(0, 10);

        // Act
        Page<AlbumResponseDTO> result = albumService.getAllAlbums(pageable);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        assertEquals("Album 1", result.getContent().get(0).getTitle());
        assertEquals("Album 2", result.getContent().get(1).getTitle());
    }

    @Test
    void testSearchAlbumsByTitleIntegration() {
        // Arrange
        Album album1 = new Album();
        album1.setTitle("Rock Album");
        album1.setArtist("Artist 1");
        album1.setYear(2020);
        albumRepository.save(album1);

        Album album2 = new Album();
        album2.setTitle("Pop Album");
        album2.setArtist("Artist 2");
        album2.setYear(2021);
        albumRepository.save(album2);

        PageRequest pageable = PageRequest.of(0, 10);

        // Act
        Page<AlbumResponseDTO> result = albumService.searchAlbumsByTitle("Rock", pageable);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("Rock Album", result.getContent().get(0).getTitle());
    }

    @Test
    void testUpdateAlbumIntegration() {
        // Arrange
        Album album = new Album();
        album.setTitle("Old Title");
        album.setArtist("Old Artist");
        album.setYear(2020);
        albumRepository.save(album);

        AlbumRequestDTO albumRequestDTO = new AlbumRequestDTO();
        albumRequestDTO.setTitle("Updated Title");
        albumRequestDTO.setArtist("Updated Artist");
        albumRequestDTO.setYear(2023);

        // Act
        AlbumResponseDTO updatedAlbumDTO = albumService.updateAlbum(album.getId(), albumRequestDTO);

        // Assert
        assertNotNull(updatedAlbumDTO);
        assertEquals("Updated Title", updatedAlbumDTO.getTitle());
        assertEquals("Updated Artist", updatedAlbumDTO.getArtist());

        Album updatedAlbum = albumRepository.findById(updatedAlbumDTO.getId()).orElse(null);
        assertNotNull(updatedAlbum);
        assertEquals("Updated Title", updatedAlbum.getTitle());
        assertEquals("Updated Artist", updatedAlbum.getArtist());
    }

    @Test
    void testDeleteAlbumIntegration() {
        // Arrange
        Album album = new Album();
        album.setTitle("Album to be deleted");
        album.setArtist("Artist");
        album.setYear(2021);
        albumRepository.save(album);

        // Act
        albumService.deleteAlbum(album.getId());

        // Assert
        assertFalse(albumRepository.existsById(album.getId()));
    }

    @Test
    void testFilterAlbumsByYearIntegration() {
        // Arrange
        Album album1 = new Album();
        album1.setTitle("Album 2020");
        album1.setArtist("Artist 1");
        album1.setYear(2020);
        albumRepository.save(album1);

        Album album2 = new Album();
        album2.setTitle("Album 2021");
        album2.setArtist("Artist 2");
        album2.setYear(2021);
        albumRepository.save(album2);

        PageRequest pageable = PageRequest.of(0, 10);

        // Act
        Page<AlbumResponseDTO> result = albumService.filterAlbumsByYear(2020, pageable);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("Album 2020", result.getContent().get(0).getTitle());
    }
}
