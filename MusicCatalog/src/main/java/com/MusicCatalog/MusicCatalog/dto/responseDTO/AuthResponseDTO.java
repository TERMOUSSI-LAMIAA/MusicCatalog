package com.MusicCatalog.MusicCatalog.dto.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthResponseDTO {
    private String id;
    private String login;
    private List<String> roles;
    private String token;
}
