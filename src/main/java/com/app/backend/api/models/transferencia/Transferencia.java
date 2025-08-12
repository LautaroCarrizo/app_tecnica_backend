package com.app.backend.api.models.transferencia;


import com.app.backend.api.models.user.User;
import com.app.backend.api.models.enums.TransferenciaEstado;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "transfers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Quién entrega (tenedor actual)
    @ManyToOne(optional = false)
    @JoinColumn(name = "from_user_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_transfer_from_user"))
    private User fromUser;

    // Quién recibe (nuevo tenedor)
    @ManyToOne(optional = false)
    @JoinColumn(name = "to_user_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_transfer_to_user"))
    private User toUser;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private TransferenciaEstado estado;

    @Column(nullable = false, updatable = false)
    private Instant requestedAt;

    private Instant respondedAt; // cuando se aceptó/rechazó (si aplica)

    // Ítems (cada uno referencia un Equipo concreto)
    @OneToMany(mappedBy = "transferencia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransferenciaItem> items = new ArrayList<>();

    @PrePersist
    void prePersist() {
        if (estado == null) estado = TransferenciaEstado.PENDIENTE;
        if (requestedAt == null) requestedAt = Instant.now();
    }
}

