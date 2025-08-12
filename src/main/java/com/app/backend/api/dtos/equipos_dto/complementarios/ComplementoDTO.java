package com.app.backend.api.dtos.equipos_dto.complementarios;

import com.app.backend.api.models.enums.EquipoEstado;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ComplementoDTO {
    private Long id;
    private EquipoEstado estado;
    private Long userId;
    private String userName;

    private String equipmentOne;
    private String equipmentTwo;
    private String equipmentThree;
    private String notes;
}