package com.app.backend.api.models.equipos;

import com.app.backend.api.models.enums.EquipoEstado;
import com.app.backend.api.models.user.User;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "equipo")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_equipo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EquipoEstado estado;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_equipment_user"))
    private User user;

    @PrePersist
    void prePersist() {
        if (estado == null) estado = EquipoEstado.FUNCIONAL;
    }
}

