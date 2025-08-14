package com.app.backend.api.repository.orders;

import com.app.backend.api.models.orders.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrderId(Long orderId);

    boolean existsByOrderIdAndEquipmentId(Long orderId, Long equipmentId);

    List<OrderItem> findByEquipmentIdOrderByCreatedAtDesc(Long equipmentId);
}
