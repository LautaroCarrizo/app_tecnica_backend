package com.app.backend.api.models.user;

import com.app.backend.api.models.equipos.Equipment;
import com.app.backend.api.models.enums.RolUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "users",
        uniqueConstraints = @UniqueConstraint(name = "uk_user_email", columnNames = "email"),
        indexes = @Index(name = "idx_user_email", columnList = "email")
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
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

    @NotBlank
    @Size(min = 60, max = 255) // BCrypt ~60 chars; dejamos margen
    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private RolUser role;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;
    
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Equipment> equipos = new ArrayList<>();

    @PrePersist
    void prePersist() {
        if (createdAt == null) createdAt = Instant.now();
        if (email != null) email = email.toLowerCase().trim();
        if (role == null) role = RolUser.USER;
    }

    @PreUpdate
    void preUpdate() {
        if (email != null) email = email.toLowerCase().trim();
    }
}

