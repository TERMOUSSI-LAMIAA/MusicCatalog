package com.MusicCatalog.MusicCatalog.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "songs")
public class Song {
    @Id
    private String id;

    @NotBlank(message = "Title cannot be empty")
    private String title;

    @NotNull(message = "Duration cannot be null")
    @Min(value = 1, message = "Duration must be at least 1 second")
    private Integer duration;

    @NotNull(message = "Track number cannot be null")
    @Min(value = 1, message = "Track number must be at least 1")
    private Integer trackNumber;

    @DBRef
    private Album album;
}
