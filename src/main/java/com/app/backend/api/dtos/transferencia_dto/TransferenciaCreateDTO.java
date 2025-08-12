package com.app.backend.api.dtos.transferencia_dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TransferenciaCreateDTO {
    @NotNull
    private Long fromUserId;
    @NotNull
    private Long toUserId;
    @NotEmpty
    private List<@NotNull Long> equipmentIds;
}