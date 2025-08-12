package com.app.backend.api.dtos.equipos_dto.pocket;

import com.app.backend.api.models.enums.EquipoEstado;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PocketDTO {
    private Long id;
    private EquipoEstado estado;
    private Long userId;
    private String userName;
}