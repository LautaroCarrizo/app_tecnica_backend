package com.app.backend.api.config.model_mapper;

import com.app.backend.api.dtos.equipments_dto.EquipmentRefDTO;
import com.app.backend.api.dtos.orders_dto.OrderDetailDTO;
import com.app.backend.api.dtos.orders_dto.OrderItemDetailDTO;
import com.app.backend.api.dtos.transferencia_dto.TransferenciaDetailDTO;
import com.app.backend.api.dtos.transferencia_dto.TransferenciaItemDetailDTO;
import com.app.backend.api.dtos.user_dto.UserRefDTO;
import com.app.backend.api.models.equipos.Equipment;
import com.app.backend.api.models.orders.Order;
import com.app.backend.api.models.orders.OrderItem;
import com.app.backend.api.models.transferencia.Transferencia;
import com.app.backend.api.models.transferencia.TransferenciaItem;
import com.app.backend.api.models.user.User;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Collectors;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mm = new ModelMapper();

        mm.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setAmbiguityIgnored(true)
                .setPreferNestedProperties(false);

        // ---------- Converters ----------
        Converter<Equipment, EquipmentRefDTO> equipmentToRef = ctx -> {
            Equipment e = ctx.getSource();
            if (e == null) return null;
            EquipmentRefDTO ref = new EquipmentRefDTO();
            ref.setId(e.getId());
            ref.setEstado(e.getEstado());
            String type = e.getKind().getTipo().name(); // PDA/CELU/...
            ref.setType(type);
            ref.setLabel(type + " " + e.getCode());     // "PDA PDA-015"
            return ref;
        };

        Converter<User, UserRefDTO> userToRef = ctx -> {
            User u = ctx.getSource();
            if (u == null) return null;
            UserRefDTO ref = new UserRefDTO();
            ref.setId(u.getId());
            ref.setName(u.getName());
            ref.setEmail(u.getEmail());
            return ref;
        };


        // OrderItem -> OrderItemDetailDTO (inyecta equipo ref)
        TypeMap<OrderItem, OrderItemDetailDTO> tmOrderItem =
                mm.createTypeMap(OrderItem.class, OrderItemDetailDTO.class);
        tmOrderItem.addMappings(m -> m.using(equipmentToRef)
                .map(OrderItem::getEquipment, OrderItemDetailDTO::setEquipment));

        // Order -> OrderDetailDTO
        TypeMap<Order, OrderDetailDTO> tmOrder =
                mm.createTypeMap(Order.class, OrderDetailDTO.class);
        tmOrder.addMappings(m -> {
            m.map(src -> (src.getUser() != null ? src.getUser().getId() : null),
                    OrderDetailDTO::setUserId);
            m.skip(OrderDetailDTO::setItems);
        });
        tmOrder.setPostConverter(ctx -> {
            Order src = ctx.getSource();
            OrderDetailDTO dst = ctx.getDestination();
            if (src.getItems() != null) {
                dst.setItems(src.getItems().stream()
                        .map(oi -> mm.map(oi, OrderItemDetailDTO.class))
                        .collect(Collectors.toList()));
            }
            return dst;
        });

        // TransferenciaItem -> TransferenciaItemDetailDTO
        TypeMap<TransferenciaItem, TransferenciaItemDetailDTO> tmTransfItem =
                mm.createTypeMap(TransferenciaItem.class, TransferenciaItemDetailDTO.class);
        tmTransfItem.addMappings(m -> m.using(equipmentToRef)
                .map(TransferenciaItem::getEquipment, TransferenciaItemDetailDTO::setEquipment));

        // Transferencia -> TransferenciaDetailDTO
        TypeMap<Transferencia, TransferenciaDetailDTO> tmTransf =
                mm.createTypeMap(Transferencia.class, TransferenciaDetailDTO.class);
        tmTransf.addMappings(m -> {
            m.using(userToRef).map(Transferencia::getFromUser, TransferenciaDetailDTO::setFromUser);
            m.using(userToRef).map(Transferencia::getToUser, TransferenciaDetailDTO::setToUser);
            m.skip(TransferenciaDetailDTO::setItems);
        });
        tmTransf.setPostConverter(ctx -> {
            Transferencia src = ctx.getSource();
            TransferenciaDetailDTO dst = ctx.getDestination();
            if (src.getItems() != null) {
                dst.setItems(src.getItems().stream()
                        .map(ti -> mm.map(ti, TransferenciaItemDetailDTO.class))
                        .collect(Collectors.toList()));
            }
            return dst;
        });

        return mm;
    }
}
