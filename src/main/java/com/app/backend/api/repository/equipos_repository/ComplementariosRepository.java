package com.app.backend.api.repository.equipos_repository;

import com.app.backend.api.models.enums.EquipoEstado;
import com.app.backend.api.models.equipos.Complementarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplementariosRepository extends JpaRepository<Complementarios, Long> {
    List<Complementarios> findByUserId(Long userId);

    long countByUserId(Long userId);

    long countByUserIdAndEstado(Long userId, EquipoEstado estado);
}
