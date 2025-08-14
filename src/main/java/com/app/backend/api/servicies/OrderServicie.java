package com.app.backend.api.servicies;
import com.app.backend.api.dtos.orders_dto.*;
import com.app.backend.api.models.enums.OrderDestino;
import com.app.backend.api.models.enums.OrderEstado;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public interface OrderServicie {
    /**
     * Crea una orden ABIERTA con ítems SOLICITADO (no mueve custodia todavía).
     */
    OrderDetailDTO crear(OrderCreateDTO dto);

    /**
     * Devuelve el detalle de una orden por id.
     */
    OrderDetailDTO obtenerPorId(Long orderId);

    /**
     * Entrega TODO el lote: valida stock y marca ítems ENTREGADO; la orden pasa a ENTREGADA.
     */
    OrderDetailDTO entregar(Long orderId);

    /**
     * Registra devolución parcial o total:
     * - Mueve custodia al stock (Técnica) para los equipos indicados.
     * - Ítems pasan a DEVUELTO.
     * - La orden pasa a PARCIALMENTE_DEVUELTA o CERRADA si vuelve todo.
     */
    OrderDetailDTO registrarDevolucion(Long orderId, List<Long> equipmentIds);

    /**
     * Marca ítems como FALTANTE (no mueve custodia).
     * La orden pasa a PARCIALMENTE_DEVUELTA o CERRADA si todo quedó devuelto/faltante.
     */
    OrderDetailDTO marcarFaltantes(Long orderId, List<Long> equipmentIdsFaltantes);

    /**
     * Cancela una orden únicamente si está ABIERTA.
     */
    void cancelar(Long orderId);

    // -------- Listados / consultas (para vistas y reportes) --------

    /**
     * Historial de órdenes de un usuario (más recientes primero).
     */
    List<OrderListDTO> listarPorUsuario(Long userId);

    /**
     * Lista por estado (ABIERTA, ENTREGADA, PARCIALMENTE_DEVUELTA, CERRADA, CANCELADA).
     */
    List<OrderListDTO> listarPorEstado(OrderEstado estado);

    /**
     * Lista por destino (INTERNO | PRODUCTOR).
     */
    List<OrderListDTO> listarPorDestino(OrderDestino destino);

    /**
     * Lista por rango de fechas de creación (más recientes primero).
     */
    List<OrderListDTO> listarPorRangoFechas(Instant desde, Instant hasta);
}
