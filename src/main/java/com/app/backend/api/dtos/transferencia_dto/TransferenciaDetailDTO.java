package com.app.backend.api.dtos.transferencia_dto;

import com.app.backend.api.dtos.user_dto.UserRefDTO;
import com.app.backend.api.models.enums.TransferenciaEstado;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TransferenciaDetailDTO {
    private Long id;
    private UserRefDTO fromUser;
    private UserRefDTO toUser;
    private TransferenciaEstado estado;
    private Instant requestedAt;
    private Instant respondedAt;
    private List<TransferenciaItemDetailDTO> items;
}