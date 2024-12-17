package com.MusicCatalog.MusicCatalog.entities;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private String id;

    @NotBlank(message = "Login cannot be empty")
    private String login;

    @NotBlank(message = "Password cannot be empty")
    private String password;

    @NotNull(message = "Active status cannot be null")
    private Boolean active;
    
    @DBRef
    private List<Role> roles;
}
