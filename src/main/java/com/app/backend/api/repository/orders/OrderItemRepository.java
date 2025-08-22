package com.app.backend.api.repository.orders;

import com.app.backend.api.models.enums.EquipoTipo;
import com.app.backend.api.models.enums.OrderItemEstado;
import com.app.backend.api.models.orders.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrderId(Long orderId);

    // Ítems de una orden filtrados por tipo de equipo (gracias al modelo central)
    List<OrderItem> findByOrderIdAndEquipment_Kind_Tipo(Long orderId, EquipoTipo tipo);

    // Validaciones/estadísticas
    boolean existsByOrderIdAndEquipment_Id(Long orderId, Long equipmentId);

    long countByOrderIdAndItemStatus(Long orderId, OrderItemEstado status);

    // Inversa: ¿en qué ítems aparece un equipo?
    List<OrderItem> findByEquipment_Id(Long equipmentId);
}
