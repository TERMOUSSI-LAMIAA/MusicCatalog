package com.MusicCatalog.MusicCatalog.repositories;

import com.MusicCatalog.MusicCatalog.entities.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SongRepository extends MongoRepository<Song, String> {
    Page<Song> findByTitleContaining(String title, Pageable pageable);

    List<Song> findByAlbumId(String albumId);
}
