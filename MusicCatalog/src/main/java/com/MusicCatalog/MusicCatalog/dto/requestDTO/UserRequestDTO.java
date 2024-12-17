package com.MusicCatalog.MusicCatalog.dto.requestDTO;

import com.MusicCatalog.MusicCatalog.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    @NotBlank(message = "Login cannot be empty")
    private String login;

    @NotBlank(message = "Password cannot be empty")
    private String password;

    @NotNull(message = "Active status cannot be null")
    private Boolean active;

    @NotNull(message = "Roles cannot be null")
    private List<Role> roles;
}
