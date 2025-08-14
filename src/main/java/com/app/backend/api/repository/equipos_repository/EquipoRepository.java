package com.app.backend.api.repository.equipos_repository;

import com.app.backend.api.models.enums.EquipoEstado;
import com.app.backend.api.models.equipos.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.Optional;

@Repository

public interface EquipoRepository extends JpaRepository<Equipo, Long> {
    List<Equipo> findByUserId(Long userId);

    long countByUserId(Long userId);

    long countByUserIdAndEstado(Long userId, EquipoEstado estado);

    Optional<Equipo> findByIdAndUserId(Long id, Long userId);
}
