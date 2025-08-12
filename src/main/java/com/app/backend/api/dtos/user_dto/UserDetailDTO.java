package com.app.backend.api.dtos.user_dto;

import com.app.backend.api.models.enums.RolUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class UserDetailDTO {
    private Long id;
    private String name;
    private String email;
    private RolUser role;
    private Instant createdAt;
}
