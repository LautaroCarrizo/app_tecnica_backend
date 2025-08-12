package com.app.backend.api.dtos.orders_dto;


import com.app.backend.api.models.enums.OrderDestino;
import com.app.backend.api.models.enums.OrderEstado;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderDetailDTO {
    private Long id;
    private String responsibility;
    private String event;
    private String notes;
    private OrderEstado state;
    private OrderDestino destination;
    private String externalReceiverName;
    private String externalReceiverDni;
    private Long userId;
    private Instant createdAt;
    private Instant deliveredAt;
    private Instant closedAt;
    private List<OrderItemDetailDTO> items;
}