package com.app.backend.api.dtos.orders_dto;

import com.app.backend.api.models.enums.OrderItemEstado;
import lombok.*;
import com.app.backend.api.dtos.equipments_dto.EquipmentRefDTO;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class OrderItemDetailDTO {
    private Long id;
    private EquipmentRefDTO equipment;
    private OrderItemEstado itemStatus;
    private Instant createdAt;
    private Instant deliveredAt;
    private Instant returnedAt;
}