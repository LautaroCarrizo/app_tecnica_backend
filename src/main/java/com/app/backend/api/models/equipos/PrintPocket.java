package com.app.backend.api.models.equipos;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pockets")
@DiscriminatorValue("POCKET")
@Getter
@Setter
@NoArgsConstructor
@Builder
public class PrintPocket extends Equipo {
}
