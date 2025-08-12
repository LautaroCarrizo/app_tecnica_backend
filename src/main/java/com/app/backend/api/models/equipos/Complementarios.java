package com.app.backend.api.models.equipos;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "complementarios")
@DiscriminatorValue("COMPLEMENTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Complementarios extends Equipo {

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String equipmentOne;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String equipmentTwo;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String equipmentThree;

    @NotBlank
    @Column(nullable = false)
    private String notes;
}
