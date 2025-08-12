package com.app.backend.api.models.equipos;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hardtickets")
@DiscriminatorValue("HARDTICKET")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrintHardTicket extends Equipo {
}
