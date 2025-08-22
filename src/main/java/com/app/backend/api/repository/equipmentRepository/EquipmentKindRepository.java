package com.app.backend.api.repository.equipmentRepository;

import com.app.backend.api.models.equipos.EquipmentKind;
import com.app.backend.api.models.enums.EquipoTipo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.List;

@Repository
public interface EquipmentKindRepository extends JpaRepository<EquipmentKind, Long> {

    Optional<EquipmentKind> findByTipoAndModeloAndMarca(EquipoTipo tipo, String modelo, String marca);

    boolean existsByTipoAndModeloAndMarca(EquipoTipo tipo, String modelo, String marca);

    List<EquipmentKind> findByTipo(EquipoTipo tipo);

    List<EquipmentKind> findByTipoIn(Collection<EquipoTipo> tipos);

    List<EquipmentKind> findByModeloContainingIgnoreCase(String modelo);

    Page<EquipmentKind> findByTipoAndModeloContainingIgnoreCase(EquipoTipo tipo, String q, Pageable pageable);
}