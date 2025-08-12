package com.app.backend.api.dtos.equipos_dto.hardticket;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class HardTicketListDTO {
    private List<HardTicketDTO> items;
    private long total;
}