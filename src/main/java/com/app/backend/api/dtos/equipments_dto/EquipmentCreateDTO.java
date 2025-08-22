package com.app.backend.api.dtos.equipments_dto;

import com.app.backend.api.models.enums.EquipoEstado;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipmentCreateDTO {

    @NotBlank
    @Size(max = 64)
    private String code;


    private EquipoEstado estado;

    @NotNull
    private Long kindId;

    @NotNull
    private Long userId;
}