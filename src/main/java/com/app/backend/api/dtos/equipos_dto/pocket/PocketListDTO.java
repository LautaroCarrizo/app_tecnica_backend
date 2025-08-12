package com.app.backend.api.dtos.equipos_dto.pocket;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PocketListDTO {
    private List<PocketDTO> items;
    private long total;
}