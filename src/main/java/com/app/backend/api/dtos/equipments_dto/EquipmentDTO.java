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
    private Long id;
    private String code;
    private EquipoEstado estado;
    private Long userId;
    private Long kindId;
    private EquipoTipo tipo;
    private String modelo;
    private String marca;
    private String label;           
}