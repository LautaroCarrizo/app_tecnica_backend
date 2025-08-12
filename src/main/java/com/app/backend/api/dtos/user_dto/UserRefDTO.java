package com.app.backend.api.dtos.user_dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRefDTO {
    private Long id;
    private String name;
    private String email;
}