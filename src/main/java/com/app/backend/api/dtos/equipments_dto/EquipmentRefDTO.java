package com.app.backend.api.dtos.equipments_dto;


import com.app.backend.api.models.enums.EquipoEstado;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipmentRefDTO {
    private Long id;
    private String type;
    private String label;
    private EquipoEstado estado;
}