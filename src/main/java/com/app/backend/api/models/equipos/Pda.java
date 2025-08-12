package com.app.backend.api.models.equipos;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "pdas")
@DiscriminatorValue("PDA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pda extends Equipo {

    @NotBlank
    @Size(max = 40)
    @Column(nullable = false, length = 40)
    private String tel;

    @NotBlank
    @Size(max = 20)
    @Column(nullable = false, length = 20)
    private String model;
}