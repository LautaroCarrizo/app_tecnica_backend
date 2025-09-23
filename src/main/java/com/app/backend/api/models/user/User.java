package com.app.backend.api.models.user;
import com.app.backend.api.models.enums.RolUser;
import com.app.backend.api.models.equipos.Equipment;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_users_email", columnNames = "email"),
                @UniqueConstraint(name = "uk_users_dni", columnNames = "dni")
        },
        indexes = {
                @Index(name = "idx_users_email", columnList = "email"),
                @Index(name = "idx_users_dni", columnList = "dni")
        }
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
    @Size(max = 80) // nombres largos como "LAUTARO CARRIZO OFFICE"
    @Column(nullable = false, length = 80)
    private String name;

    @Email
    @Size(max = 120)
    @Column(nullable = true, length = 120)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private RolUser role; // USER / ADMIN

    @NotBlank
    @Size(max = 20)
    @Column(name = "dni", nullable = false, length = 20, unique = true)
    private String dni;

    @Column(name = "external_user_id", unique = true)
    private Long externalUserId;

    @Column(name = "external_enabled")
    private Boolean externalEnabled;

    @Column(name = "external_blocked")
    private Boolean externalBlocked;

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
        if (dni != null) dni = dni.trim();
        if (name != null) name = name.trim();
    }

    @PreUpdate
    void preUpdate() {
        if (email != null) email = email.toLowerCase().trim();
        if (dni != null) dni = dni.trim();
        if (name != null) name = name.trim();
    }

    public boolean isAdmin() {
        return RolUser.ADMIN.equals(role);
    }
}