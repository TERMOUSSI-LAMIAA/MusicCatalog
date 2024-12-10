package com.MusicCatalog.MusicCatalog.dto.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AlbumRequestDTO {
    @NotBlank(message = "Title cannot be empty")
    private String title;

    @NotBlank(message = "Artist cannot be empty")
    private String artist;

    @NotNull(message = "Year cannot be null")
    @Max(value = 2024, message = "Year cannot be in the future")
    private Integer year;
}
