package com.MusicCatalog.MusicCatalog.dto.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class AlbumResponseDTO {


    private String id;
    private String title;
    private String artist;
    private Integer year;
    private List<SongResponseDTO> songs;
}
