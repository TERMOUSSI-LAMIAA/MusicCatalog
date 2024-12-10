package com.MusicCatalog.MusicCatalog.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be empty")
    private String title;

    @NotBlank(message = "Artist cannot be empty")
    private String artist;

    @NotNull(message = "Year cannot be null")
    @Max(value = 2024, message = "Year cannot be in the future")
    private Integer year;

}
