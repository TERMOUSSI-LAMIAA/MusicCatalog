package com.MusicCatalog.MusicCatalog.exceptions;

public class AlbumException extends RuntimeException{
    public AlbumException(Long id){
        super("Album not found with the id :"+id);
    }
}
