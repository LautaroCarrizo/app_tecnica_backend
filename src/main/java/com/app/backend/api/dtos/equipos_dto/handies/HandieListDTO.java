package com.app.backend.api.dtos.equipos_dto.handies;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class HandieListDTO {
    private List<HandieDTO> items;
    private long total;
}