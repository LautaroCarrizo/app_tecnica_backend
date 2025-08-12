package com.app.backend.api.models.equipos;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "handies")
@DiscriminatorValue("HANDIE")
@Getter
@Setter
@NoArgsConstructor
@Builder
public class Handie extends Equipo {
    // sin campos extra en tu JS
}
