package com.app.backend.api.models.equipos;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "celus")
@DiscriminatorValue("CELU")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Celu extends Equipo {

    @NotBlank
    @Size(max = 40)
    @Column(nullable = false, length = 40)
    private String tel;

    @NotBlank
    @Size(max = 20)
    @Column(nullable = false, length = 20)
    private String model;
}
