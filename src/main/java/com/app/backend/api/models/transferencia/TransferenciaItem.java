package com.app.backend.api.models.transferencia;


import com.app.backend.api.models.equipos.Equipo;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "transfer_items",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_transfer_equipment",
                columnNames = {"transfer_id", "equipment_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferenciaItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "transfer_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_transferitem_transfer"))
    private Transferencia transferencia;

    @ManyToOne(optional = false)
    @JoinColumn(name = "equipment_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_transferitem_equipment"))
    private Equipo equipment;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    private Instant acceptedAt;

    @PrePersist
    void prePersist() {
        if (createdAt == null) createdAt = Instant.now();
    }
}

