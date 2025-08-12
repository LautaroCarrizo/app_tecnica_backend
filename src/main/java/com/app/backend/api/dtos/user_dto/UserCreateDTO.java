package com.app.backend.api.dtos.user_dto;

import com.app.backend.api.models.enums.RolUser;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserCreateDTO {
    @NotBlank
    @Size(max = 40)
    private String name;
    @NotBlank
    @Email
    @Size(max = 120)
    private String email;
    @NotBlank
    @Size(min = 8, max = 128)
    private String password;
    private RolUser role;
}