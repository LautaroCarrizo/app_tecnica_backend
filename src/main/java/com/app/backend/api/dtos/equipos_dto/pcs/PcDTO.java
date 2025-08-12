package com.app.backend.api.dtos.equipos_dto.pcs;

import com.app.backend.api.models.enums.EquipoEstado;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PcDTO {
    private Long id;
    private EquipoEstado estado;
    private Long userId;
    private String userName;

    private String model;
    private String specifications;
    private String notes;
}