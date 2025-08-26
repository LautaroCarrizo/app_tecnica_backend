package com.app.backend.api.models.orders;


import com.app.backend.api.models.enums.OrderItemEstado;
import com.app.backend.api.models.equipos.Equipment;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "order_items",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_order_equipment",
                columnNames = {"order_id", "equipment_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_orderitem_order"))
    private Order order;

    @ManyToOne(optional = false)
    @JoinColumn(name = "equipment_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_orderitem_equipment"))
    private Equipment equipment;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private OrderItemEstado itemStatus;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    private Instant deliveredAt; // cuándo salió este equipo puntual
    private Instant returnedAt;  // cuándo volvió este equipo puntual

    @PrePersist
    void prePersist() {
        if (itemStatus == null) itemStatus = OrderItemEstado.SOLICITADO;
        if (createdAt == null) createdAt = Instant.now();
    }
}
