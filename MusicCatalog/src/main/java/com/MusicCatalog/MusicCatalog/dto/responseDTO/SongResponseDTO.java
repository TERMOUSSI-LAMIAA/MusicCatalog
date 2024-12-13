package com.MusicCatalog.MusicCatalog.dto.responseDTO;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongResponseDTO {

    private String id;
    private String title;
    private Integer duration;
    private Integer trackNumber;

    private String albumId;
}
