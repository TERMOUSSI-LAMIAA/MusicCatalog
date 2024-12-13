package com.MusicCatalog.MusicCatalog.exceptions;

public class SongException  extends RuntimeException{
    public SongException(String id){
        super("Song not found with id: "+id);
    }
}
