package com.app.backend.api.dtos.transferencia_dto;

import com.app.backend.api.models.enums.TransferenciaEstado;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class TransferenciaListDTO {
    private Long id;
    private Long fromUserId;
    private Long toUserId;
    private TransferenciaEstado estado;
    private Instant requestedAt;
}