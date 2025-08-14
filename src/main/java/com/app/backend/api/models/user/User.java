package com.app.backend.api.models.user;

import com.app.backend.api.models.enums.RolUser;
import com.app.backend.api.models.equipos.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(name = "uk_users_email", columnNames = "email")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 40)
    @Column(nullable = false, length = 40)
    private String name;

    @NotBlank
    @Email
    @Size(max = 120)
    @Column(nullable = false, length = 120)
    private String email;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private RolUser role;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;


    @OneToMany(mappedBy = "user")
    private List<Equipo> equipos = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Celu> celus = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<Pda> pdas = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<Pc> pcs = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<Handie> handies = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<PrintPocket> pockets = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<PrintHardTicket> hardTickets = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<Complementarios> complementos = new ArrayList<>();

    @PrePersist
    void prePersist() {
        if (createdAt == null) createdAt = Instant.now();
        if (email != null) email = email.toLowerCase().trim();
        if (role == null) role = RolUser.USER;
    }
}
