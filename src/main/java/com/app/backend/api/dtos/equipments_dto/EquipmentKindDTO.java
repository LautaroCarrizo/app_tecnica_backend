package com.app.backend.api.dtos.equipments_dto;

import com.app.backend.api.models.enums.EquipoTipo;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipmentKindDTO {
    private Long id;
    private EquipoTipo tipo;   // PDA, CELU, PC, ...
    private String modelo;     // p.ej. "Zebra TC25"
    private String marca;      // opcional
    private String notas;      // opcional
}