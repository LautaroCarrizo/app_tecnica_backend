package com.app.backend.api.dtos.equipos_dto;


import com.app.backend.api.models.enums.EquipoEstado;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class EquipoRefDTO {
    private Long id;
    private String tipo;
    private EquipoEstado estado;
    private String label;
}