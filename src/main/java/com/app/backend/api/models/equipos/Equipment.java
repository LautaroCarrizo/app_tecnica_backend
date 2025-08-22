package com.app.backend.api.models.equipos;

import com.app.backend.api.models.enums.EquipoEstado;
import com.app.backend.api.models.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "equipment",
        indexes = {
                @Index(name = "idx_equipment_user", columnList = "user_id"),
                @Index(name = "idx_equipment_kind", columnList = "kind_id"),
                @Index(name = "idx_equipment_estado", columnList = "estado")
        })
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Código físico/IMEI/serial visible en el equipo
    @Column(name = "code", nullable = false, unique = true, length = 64)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    @Builder.Default
    private EquipoEstado estado = EquipoEstado.FUNCIONAL;

    // Catálogo (SKU/modelo)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "kind_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_equipment_kind"))
    private EquipmentKind kind;

    // Custodia actual (stock = usuario Técnica)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_equipment_user"))
    private User user;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    void prePersist() {
        if (createdAt == null) createdAt = Instant.now();
        if (estado == null) estado = EquipoEstado.FUNCIONAL;
    }
}