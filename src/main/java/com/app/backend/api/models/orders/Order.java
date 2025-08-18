package com.app.backend.api.models.orders;

import com.app.backend.api.models.enums.OrderDestino;
import com.app.backend.api.models.user.User;
import com.app.backend.api.models.enums.OrderEstado;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 40)
    @Column(nullable = false, length = 40)
    private String responsibility;

    @NotBlank
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String event;

    @Size(max = 60)
    @Column(length = 60)
    private String notes;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false, length = 25)
    private OrderEstado state;


    @ManyToOne(optional = false)
    @JoinColumn(name = "userId", nullable = false,
            foreignKey = @ForeignKey(name = "fk_orders_user"))
    private User user;


    @Enumerated(EnumType.STRING)
    @Column(name = "destination", nullable = false, length = 15)
    private OrderDestino destination;


    @Size(max = 80)
    @Column(name = "external_receiver_name", length = 80)
    private String externalReceiverName;

    @Size(max = 20)
    @Column(name = "external_receiver_dni", length = 20)
    private String externalReceiverDni;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<OrderItem> items = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    private Instant deliveredAt;
    private Instant closedAt;

    @PrePersist
    void prePersist() {
        if (state == null) state = OrderEstado.ABIERTA;
        if (destination == null) destination = OrderDestino.INTERNO;
        if (createdAt == null) createdAt = Instant.now();
    }
}