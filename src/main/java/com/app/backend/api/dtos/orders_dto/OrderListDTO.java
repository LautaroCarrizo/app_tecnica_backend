package com.app.backend.api.dtos.orders_dto;

import com.app.backend.api.models.enums.OrderDestino;
import com.app.backend.api.models.enums.OrderEstado;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class OrderListDTO {
    private Long id;
    private String responsibility;
    private String event;
    private OrderEstado state;
    private OrderDestino destination;
    private Instant createdAt;
}
