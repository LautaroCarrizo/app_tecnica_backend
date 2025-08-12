package com.app.backend.api.dtos.equipos_dto.complementarios;

import com.app.backend.api.models.enums.EquipoEstado;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PdaDTO {
    private Long id;
    private EquipoEstado estado;
    private Long userId;
    private String userName;

    private String tel;
    private String model;
}
