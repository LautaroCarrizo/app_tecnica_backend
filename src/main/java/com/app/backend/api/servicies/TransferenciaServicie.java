package com.app.backend.api.servicies;

import com.app.backend.api.dtos.transferencia_dto.TransferenciaCreateDTO;
import com.app.backend.api.dtos.transferencia_dto.TransferenciaDetailDTO;
import com.app.backend.api.dtos.transferencia_dto.TransferenciaListDTO;
import com.app.backend.api.models.enums.TransferenciaEstado;

import java.time.Instant;
import java.util.List;

public interface TransferenciaServicie {

    /**
     * Crea una transferencia PENDIENTE validando que los equipos estén hoy en custodia de fromUser.
     */
    TransferenciaDetailDTO crear(TransferenciaCreateDTO dto);

    /**
     * Devuelve el detalle completo de una transferencia (from/to, estado, fechas, items).
     */
    TransferenciaDetailDTO obtenerPorId(Long transferenciaId);

    /**
     * Acepta una transferencia PENDIENTE (solo el destinatario toUser). Mueve custodia y marca acceptedAt.
     */
    TransferenciaDetailDTO aceptar(Long transferenciaId, Long aprobadorUserId);

    /**
     * Rechaza una transferencia PENDIENTE (solo el destinatario). No cambia custodia.
     */
    TransferenciaDetailDTO rechazar(Long transferenciaId, Long aprobadorUserId);

    /**
     * Cancela una transferencia PENDIENTE (emisor o admin). No cambia custodia.
     */
    TransferenciaDetailDTO cancelar(Long transferenciaId, Long solicitanteUserId, boolean esAdmin);

    // Bandejas / reportes

    /**
     * Transferencias ENVIADAS por un usuario (fromUser), más recientes primero.
     */
    List<TransferenciaListDTO> listarEnviadas(Long fromUserId);

    /**
     * Transferencias RECIBIDAS por un usuario (toUser), más recientes primero.
     */
    List<TransferenciaListDTO> listarRecibidas(Long toUserId);

    /**
     * Lista por estado (PENDIENTE/ACEPTADA/RECHAZADA/CANCELADA).
     */
    List<TransferenciaListDTO> listarPorEstado(TransferenciaEstado estado);

    /**
     * Lista por rango de fechas (requestedAt).
     */
    List<TransferenciaListDTO> listarPorRangoFechas(Instant desde, Instant hasta);
}
