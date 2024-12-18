package com.MusicCatalog.MusicCatalog;

import com.MusicCatalog.MusicCatalog.dto.requestDTO.AlbumRequestDTO;
import com.MusicCatalog.MusicCatalog.dto.responseDTO.AlbumResponseDTO;
import com.MusicCatalog.MusicCatalog.entities.Album;
import com.MusicCatalog.MusicCatalog.exceptions.AlbumException;
import com.MusicCatalog.MusicCatalog.mappers.AlbumMapper;
import com.MusicCatalog.MusicCatalog.repositories.AlbumRepository;
import com.MusicCatalog.MusicCatalog.services.AlbumService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AlbumServiceTest {

    @Mock
    private AlbumRepository albumRepository;

    @Mock
    private AlbumMapper albumMapper;

    @InjectMocks
    private AlbumService albumService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAlbums() {
        Pageable pageable = mock(Pageable.class);
        Album album = new Album();
        Page<Album> albumPage = new PageImpl<>(Collections.singletonList(album));
        when(albumRepository.findAll(pageable)).thenReturn(albumPage);

        AlbumResponseDTO albumResponseDTO = new AlbumResponseDTO();
        when(albumMapper.toDTO(album)).thenReturn(albumResponseDTO);

        Page<AlbumResponseDTO> result = albumService.getAllAlbums(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(albumRepository).findAll(pageable);
        verify(albumMapper).toDTO(album);
    }
    @Test
    void testGetAlbumById() {
        String albumId = "123";
        Album album = new Album();
        when(albumRepository.findById(albumId)).thenReturn(Optional.of(album));

        AlbumResponseDTO albumResponseDTO = new AlbumResponseDTO();
        when(albumMapper.toDTO(album)).thenReturn(albumResponseDTO);

        AlbumResponseDTO result = albumService.getAlbumById(albumId);

        assertNotNull(result);
        verify(albumRepository).findById(albumId);
        verify(albumMapper).toDTO(album);
    }

    @Test
    void testGetAlbumById_NotFound() {
        String albumId = "123";
        when(albumRepository.findById(albumId)).thenReturn(Optional.empty());

        assertThrows(AlbumException.class, () -> albumService.getAlbumById(albumId));
        verify(albumRepository).findById(albumId);
    }

    @Test
    void testCreateAlbum() {
        AlbumRequestDTO albumRequestDTO = new AlbumRequestDTO();
        Album album = new Album();
        when(albumMapper.toEntity(albumRequestDTO)).thenReturn(album);

        Album savedAlbum = new Album();
        when(albumRepository.save(album)).thenReturn(savedAlbum);

        AlbumResponseDTO albumResponseDTO = new AlbumResponseDTO();
        when(albumMapper.toDTO(savedAlbum)).thenReturn(albumResponseDTO);

        AlbumResponseDTO result = albumService.createAlbum(albumRequestDTO);

        assertNotNull(result);
        verify(albumMapper).toEntity(albumRequestDTO);
        verify(albumRepository).save(album);
        verify(albumMapper).toDTO(savedAlbum);
    }
    @Test
    void testUpdateAlbum() {
        String albumId = "123";
        AlbumRequestDTO albumRequestDTO = new AlbumRequestDTO();
        Album album = new Album();
        when(albumRepository.findById(albumId)).thenReturn(Optional.of(album));

        Album updatedAlbum = new Album();
        doAnswer(invocation -> {
            AlbumRequestDTO dto = invocation.getArgument(0);
            Album entity = invocation.getArgument(1);
            // Mock the mapper behavior here if needed
            return null;
        }).when(albumMapper).updateEntityFromDTO(albumRequestDTO, album);

        when(albumRepository.save(album)).thenReturn(updatedAlbum);

        AlbumResponseDTO albumResponseDTO = new AlbumResponseDTO();
        when(albumMapper.toDTO(updatedAlbum)).thenReturn(albumResponseDTO);

        AlbumResponseDTO result = albumService.updateAlbum(albumId, albumRequestDTO);

        assertNotNull(result);
        verify(albumRepository).findById(albumId);
        verify(albumMapper).updateEntityFromDTO(albumRequestDTO, album);
        verify(albumRepository).save(album);
        verify(albumMapper).toDTO(updatedAlbum);
    }
    @Test
    void testDeleteAlbum() {
        String albumId = "123";
        Album album = new Album();
        when(albumRepository.findById(albumId)).thenReturn(Optional.of(album));

        albumService.deleteAlbum(albumId);

        verify(albumRepository).findById(albumId);
        verify(albumRepository).delete(album);
    }

}
