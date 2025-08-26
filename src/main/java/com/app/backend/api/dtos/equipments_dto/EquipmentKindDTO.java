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
    private EquipoTipo tipo;
    private String modelo;
    private String marca;
    private String notas;
}