package com.app.backend.api.dtos.equipments_dto;

import com.app.backend.api.models.enums.EquipoEstado;
import com.app.backend.api.models.enums.EquipoTipo;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipmentDTO {
    private Long id;                 // PK técnica
    private String code;             // "PDA-015" (único)
    private EquipoEstado estado;     // FUNCIONAL / NO_FUNCIONAL

    private Long userId;             // custodia actual (Técnica/usuario)
    private Long kindId;             // FK al catálogo

    // Datos “levantados” del kind para facilitar al front:
    private EquipoTipo tipo;         // PDA/CELU/...
    private String modelo;           // "Zebra TC25"
    private String marca;            // opcional

    private String label;            // p.ej. "PDA PDA-015"
}