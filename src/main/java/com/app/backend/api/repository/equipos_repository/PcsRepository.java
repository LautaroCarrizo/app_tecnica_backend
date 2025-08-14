package com.app.backend.api.repository.equipos_repository;

import com.app.backend.api.models.enums.EquipoEstado;
import com.app.backend.api.models.equipos.Pc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PcsRepository extends JpaRepository<Pc, Long> {
    List<Pc> findByUserId(Long userId);

    long countByUserId(Long userId);

    long countByUserIdAndEstado(Long userId, EquipoEstado estado);
}