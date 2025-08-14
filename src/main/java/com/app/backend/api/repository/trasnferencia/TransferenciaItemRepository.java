package com.app.backend.api.repository.trasnferencia;

import com.app.backend.api.models.transferencia.TransferenciaItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferenciaItemRepository extends JpaRepository<TransferenciaItem, Long> {
    List<TransferenciaItem> findByTransferenciaId(Long transferenciaId);

    boolean existsByTransferenciaIdAndEquipmentId(Long transferenciaId, Long equipmentId);
}
