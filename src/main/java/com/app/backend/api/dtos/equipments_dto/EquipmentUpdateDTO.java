package com.app.backend.api.dtos.equipments_dto;

import com.app.backend.api.models.enums.EquipoEstado;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipmentUpdateDTO {

    private String code;
    private EquipoEstado estado;
    private Long kindId;
    private Long userId;
}