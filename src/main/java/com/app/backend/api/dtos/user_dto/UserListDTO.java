package com.app.backend.api.dtos.user_dto;

import com.app.backend.api.models.enums.RolUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserListDTO {
    private Long id;
    private String name;
    private String email;
    private RolUser role;
}