package com.app.backend.api.dtos.equipos_dto.complementarios;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class ComplementoListDTO {
    private List<ComplementoDTO> items;
    private long total;
}
