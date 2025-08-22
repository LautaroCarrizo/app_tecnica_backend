package com.app.backend.api.repository.equipmentRepository;

import com.app.backend.api.models.equipos.Equipment;
import com.app.backend.api.models.enums.EquipoEstado;
import com.app.backend.api.models.enums.EquipoTipo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {


    Optional<Equipment> findByCode(String code);

    boolean existsByCode(String code);

    // Custodia actual
    List<Equipment> findByUserId(Long userId);

    Page<Equipment> findByUserId(Long userId, Pageable pageable);

    // Filtros por catálogo
    List<Equipment> findByKind_Id(Long kindId);

    List<Equipment> findByKind_Tipo(EquipoTipo tipo);

    List<Equipment> findByUserIdAndKind_Tipo(Long userId, EquipoTipo tipo);

    // Estado técnico / contadores de stock
    List<Equipment> findByEstado(EquipoEstado estado);

    long countByKind_Tipo(EquipoTipo tipo);

    long countByKind_TipoAndEstado(EquipoTipo tipo, EquipoEstado estado);

    long countByUserIdAndEstado(Long userId, EquipoEstado estado);

    // Búsqueda por código (para autocompletar, etc.)
    List<Equipment> findByCodeContainingIgnoreCase(String codePart);
}