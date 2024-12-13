package com.MusicCatalog.MusicCatalog.repositories;

import com.MusicCatalog.MusicCatalog.entities.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends MongoRepository<Album, String> {
    Page<Album> findByTitleContaining(String title, Pageable pageable);
    List<Album> findByArtistContaining(String artist);
    Page<Album> findByYear(int year, Pageable pageable);
}
