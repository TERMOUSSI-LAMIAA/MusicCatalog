package com.MusicCatalog.MusicCatalog.dto.requestDTO;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDTO {

    @NotBlank(message = "login is required")
    private String login;

    @NotBlank(message = "password is required")
    private String password;

}
