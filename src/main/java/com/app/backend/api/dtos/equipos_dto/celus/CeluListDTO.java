package com.app.backend.api.dtos.equipos_dto.celus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CeluListDTO {
    private List<CeluDTO> items;
    private long total;
}