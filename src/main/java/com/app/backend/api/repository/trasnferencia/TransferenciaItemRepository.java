package com.app.backend.api.repository.trasnferencia;

import com.app.backend.api.models.enums.EquipoTipo;
import com.app.backend.api.models.transferencia.TransferenciaItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferenciaItemRepository extends JpaRepository<TransferenciaItem, Long> {

    List<TransferenciaItem> findByTransferenciaId(Long transferenciaId);

    // Ítems de una transferencia por tipo
    List<TransferenciaItem> findByTransferenciaIdAndEquipment_Kind_Tipo(Long transferenciaId, EquipoTipo tipo);

    // Inversa: ¿en qué transferencias apareció este equipo?
    List<TransferenciaItem> findByEquipment_Id(Long equipmentId);
}