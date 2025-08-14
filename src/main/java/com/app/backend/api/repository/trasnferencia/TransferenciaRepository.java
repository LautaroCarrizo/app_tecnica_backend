package com.app.backend.api.repository.trasnferencia;

import com.app.backend.api.models.enums.TransferenciaEstado;
import com.app.backend.api.models.transferencia.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
    List<Transferencia> findByFromUserIdOrderByRequestedAtDesc(Long fromUserId);

    List<Transferencia> findByToUserIdOrderByRequestedAtDesc(Long toUserId);

    List<Transferencia> findByEstadoOrderByRequestedAtDesc(TransferenciaEstado estado);

    List<Transferencia> findByRequestedAtBetweenOrderByRequestedAtDesc(Instant from, Instant to);
}