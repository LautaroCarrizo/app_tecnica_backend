package com.app.backend.api.models.equipos;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "pcs")
@DiscriminatorValue("PC")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pc extends Equipo {

    @NotBlank
    @Size(max = 30)
    @Column(nullable = false, length = 30)
    private String model;

    @NotBlank
    @Size(max = 150)
    @Column(nullable = false, length = 150)
    private String specifications;

    @Size(max = 150)
    @Column(length = 150)
    private String notes;
}
