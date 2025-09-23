package com.app.backend.api.services_imp.order;

import com.app.backend.api.dtos.orders_dto.OrderCreateDTO;
import com.app.backend.api.dtos.orders_dto.OrderDetailDTO;
import com.app.backend.api.dtos.orders_dto.OrderListDTO;
import com.app.backend.api.repository.equipmentRepository.EquipmentRepository;
import com.app.backend.api.repository.orders.OrderItemRepository;
import com.app.backend.api.repository.orders.OrdersRepository;
import com.app.backend.api.repository.users.UserRepository;
import com.app.backend.api.services.OrderService;
import com.app.backend.api.models.enums.OrderEstado;
import com.app.backend.api.models.enums.OrderDestino;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.Instant;
import java.util.*;

@RequiredArgsConstructor
@Service
public class OrderServiceImp implements OrderService {

    private final OrdersRepository order_repo;
    private final OrderItemRepository order_item_repo;
    private final UserRepository user_repo;
    private final EquipmentRepository equipo_repo;
    private final ModelMapper modelMapper;

    // Provisorio: usuario "Técnica" (dueño del stock)
    private static final Long STOCK_USER_ID = 1L;

    @Override
    @Transactional
    public OrderDetailDTO crear(OrderCreateDTO dto) {
//        User userInterno = user_repo.findById(dto.getUserId()).orElseThrow(() -> new EntityNotFoundException("Usuario interno no encontrado"));
//        String productorNombre = dto.getDestination() == OrderDestino.PRODUCTOR ? dto.getExternalReceiverName() : null;
//        String productorDni = dto.getDestination() == OrderDestino.PRODUCTOR ? dto.getExternalReceiverDni() : null;
//        Order newOrder = Order.builder().responsibility(dto.getResponsibility()).event(dto.getEvent()).notes(dto.getNotes()).destination(dto.getDestination()).externalReceiverName(productorNombre).externalReceiverDni(productorDni).user(userInterno).state(OrderEstado.ABIERTA).createdAt(Instant.now()).build();
//        newOrder = order_repo.save(newOrder);
//        Set<Long> uniqueItem = new HashSet<>();
//        for (Long eqId : dto.getEquipmentIds()) {
//            if (!uniqueItem.add(eqId)) continue;
//            Equipment eq = equipo_repo.findById(eqId).orElseThrow(() -> new EntityNotFoundException("Equipo id=" + eqId + " no existe"));
//            OrderItem item = OrderItem.builder().order(newOrder).equipment(eq).itemStatus(OrderItemEstado.SOLICITADO).createdAt(Instant.now()).build();
//            order_item_repo.save(item);
//            newOrder.getItems().add(item);
//        }
//        return modelMapper.map(newOrder, OrderDetailDTO.class);
        return null;
    }

    @Override
    public OrderDetailDTO obtenerPorId(Long orderId) {
        return null;
    }

    @Override
    public OrderDetailDTO entregar(Long orderId) {
        return null;
    }

    @Override
    public OrderDetailDTO registrarDevolucion(Long orderId, List<Long> equipmentIds) {
        return null;
    }

    @Override
    public OrderDetailDTO marcarFaltantes(Long orderId, List<Long> equipmentIdsFaltantes) {
        return null;
    }

    @Override
    public void cancelar(Long orderId) {

    }

    @Override
    public List<OrderListDTO> listarPorUsuario(Long userId) {
        return List.of();
    }

    @Override
    public List<OrderListDTO> listarPorEstado(OrderEstado estado) {
        return List.of();
    }

    @Override
    public List<OrderListDTO> listarPorDestino(OrderDestino destino) {
        return List.of();
    }

    @Override
    public List<OrderListDTO> listarPorRangoFechas(Instant desde, Instant hasta) {
        return List.of();
    }
}
