package com.app.backend.api.dtos.equipos_dto.pcs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PcListDTO {
    private List<PcDTO> items;
    private long total;
}