package com.app.backend.api.dtos.equipos_dto.pdas;


import com.app.backend.api.dtos.equipos_dto.complementarios.PdaDTO;
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
