package com.MusicCatalog.MusicCatalog.dto.requestDTO;

import lombok.*;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongRequestDTO {

    @NotBlank(message = "Title cannot be empty")
    private String title;

    @NotNull(message = "Duration cannot be null")
    @Min(value = 1, message = "Duration must be at least 1 second")
    private Integer duration;

    @NotNull(message = "Track number cannot be null")
    @Min(value = 1, message = "Track number must be at least 1")
    private Integer trackNumber;

    @NotNull(message = "Album ID cannot be null")
    private String albumId;
}
