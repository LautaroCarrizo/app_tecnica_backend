package com.app.backend.api.dtos.transferencia_dto;

import com.app.backend.api.dtos.equipments_dto.EquipoRefDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class TransferenciaItemDetailDTO {
    private Long id;
    private EquipoRefDTO equipment;
    private Instant createdAt;
    private Instant acceptedAt;
}