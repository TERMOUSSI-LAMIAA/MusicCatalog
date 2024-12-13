package com.MusicCatalog.MusicCatalog.exceptions;

public class UserException extends RuntimeException{
    public UserException(String id){
        super("User not found with the id :"+id);
    }
}
