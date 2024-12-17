package com.MusicCatalog.MusicCatalog.dto.responseDTO;

import com.MusicCatalog.MusicCatalog.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponseDTO {

    private String id;
    private String login;
    private Boolean active;
    private List<Role> roles;
}
