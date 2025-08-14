package com.app.backend.api.repository.orders;

import com.app.backend.api.models.enums.OrderDestino;
import com.app.backend.api.models.enums.OrderEstado;
import com.app.backend.api.models.orders.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserIdOrderByCreatedAtDesc(Long userId);

    List<Order> findByStateOrderByCreatedAtDesc(OrderEstado state);

    List<Order> findByDestinationOrderByCreatedAtDesc(OrderDestino destination);

    List<Order> findByCreatedAtBetweenOrderByCreatedAtDesc(Instant from, Instant to);
}

