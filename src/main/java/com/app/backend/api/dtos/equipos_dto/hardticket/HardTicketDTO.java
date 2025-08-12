package com.app.backend.api.dtos.equipos_dto.hardticket;

import com.app.backend.api.models.enums.EquipoEstado;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HardTicketDTO {
    private Long id;
    private EquipoEstado estado;
    private Long userId;
    private String userName;
}