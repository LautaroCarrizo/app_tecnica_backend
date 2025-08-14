package com.app.backend.api.dtos.equipos_dto.pdas;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PdaListDTO {
    private List<PdaDTO> items;
    private long total;
}
