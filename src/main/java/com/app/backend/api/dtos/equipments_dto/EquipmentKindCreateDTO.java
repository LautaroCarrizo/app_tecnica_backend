package com.app.backend.api.dtos.equipments_dto;


import com.app.backend.api.models.enums.EquipoTipo;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipmentKindCreateDTO {
    @NotNull
    private EquipoTipo tipo;

    @NotBlank
    @Size(max = 100)
    private String modelo;

    @Size(max = 100)
    private String marca;

    @Size(max = 255)
    private String notas;
}