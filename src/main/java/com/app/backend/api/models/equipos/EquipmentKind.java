package com.app.backend.api.models.equipos;


import com.app.backend.api.models.enums.EquipoTipo;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "equipment_kinds",
        uniqueConstraints = @UniqueConstraint(name = "uk_kind_tipo_modelo_marca",
                columnNames = {"tipo", "modelo", "marca"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipmentKind {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 30)
    private EquipoTipo tipo;               // PDA | CELU | ...

    @Column(name = "modelo", nullable = false, length = 100)
    private String modelo;

    @Column(name = "marca", length = 100)
    private String marca;

    @Column(name = "notas", length = 255)
    private String notas;
}