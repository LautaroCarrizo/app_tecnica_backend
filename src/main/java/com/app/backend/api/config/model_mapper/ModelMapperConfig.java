package com.app.backend.api.config.model_mapper;

import com.app.backend.api.dtos.equipos_dto.EquipoRefDTO;
import com.app.backend.api.dtos.orders_dto.OrderDetailDTO;
import com.app.backend.api.dtos.orders_dto.OrderItemDetailDTO;
import com.app.backend.api.dtos.transferencia_dto.TransferenciaDetailDTO;
import com.app.backend.api.dtos.transferencia_dto.TransferenciaItemDetailDTO;
import com.app.backend.api.dtos.user_dto.UserRefDTO;
import com.app.backend.api.models.equipos.Equipo;
import com.app.backend.api.models.orders.Order;
import com.app.backend.api.models.orders.OrderItem;
import com.app.backend.api.models.transferencia.Transferencia;
import com.app.backend.api.models.transferencia.TransferenciaItem;
import com.app.backend.api.models.user.User; // ⬅️ ajustá si tu clase real se llama Usuario
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Collectors;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mm = new ModelMapper();

        // 1) Config global: matching estricto y acceso a campos
        mm.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT);

        // 2) Converters reusables

        // Equipo -> EquipoRefDTO (resumen liviano para items)
        Converter<Equipo, EquipoRefDTO> equipoToRef = ctx -> {
            Equipo e = ctx.getSource();
            if (e == null) return null;
            EquipoRefDTO ref = new EquipoRefDTO();
            ref.setId(e.getId());
            ref.setEstado(e.getEstado());
            String type = e.getClass().getSimpleName().toUpperCase();
            ref.setType(type);
            ref.setLabel(type + " #" + e.getId());
            return ref;
        };

        // User -> UserRefDTO (para Transferencia.from/to)
        Converter<User, UserRefDTO> userToRef = ctx -> {
            User u = ctx.getSource();
            if (u == null) return null;
            UserRefDTO ref = new UserRefDTO();
            ref.setId(u.getId());
            ref.setName(u.getName());
            ref.setEmail(u.getEmail());
            return ref;
        };

        // 3) TypeMaps

        // OrderItem -> OrderItemDetailDTO (inyecta equipmentRef)
        mm.typeMap(OrderItem.class, OrderItemDetailDTO.class)
                .addMappings(m -> m.using(equipoToRef)
                        .map(OrderItem::getEquipment, OrderItemDetailDTO::setEquipment));

        // Order -> OrderDetailDTO
        // - mapear user.id -> userId
        // - mapear items usando el typeMap de arriba (postConverter)
        mm.typeMap(Order.class, OrderDetailDTO.class)
                .addMappings(m -> {
                    m.map(src -> (src.getUser() != null ? src.getUser().getId() : null),
                            OrderDetailDTO::setUserId);
                    m.skip(OrderDetailDTO::setItems); // se arma abajo
                })
                .setPostConverter(ctx -> {
                    Order src = ctx.getSource();
                    OrderDetailDTO dst = ctx.getDestination();
                    if (src.getItems() != null) {
                        dst.setItems(src.getItems().stream()
                                .map(oi -> mm.map(oi, OrderItemDetailDTO.class))
                                .collect(Collectors.toList()));
                    }
                    return dst;
                });

        // TransferenciaItem -> TransferenciaItemDetailDTO (inyecta equipmentRef)
        mm.typeMap(TransferenciaItem.class, TransferenciaItemDetailDTO.class)
                .addMappings(m -> m.using(equipoToRef)
                        .map(TransferenciaItem::getEquipment, TransferenciaItemDetailDTO::setEquipment));

        // Transferencia -> TransferenciaDetailDTO
        // - fromUser/toUser a UserRefDTO con converter
        // - items usando el typeMap anterior
        mm.typeMap(Transferencia.class, TransferenciaDetailDTO.class)
                .addMappings(m -> {
                    m.using(userToRef).map(Transferencia::getFromUser, TransferenciaDetailDTO::setFromUser);
                    m.using(userToRef).map(Transferencia::getToUser, TransferenciaDetailDTO::setToUser);
                    m.skip(TransferenciaDetailDTO::setItems);
                })
                .setPostConverter(ctx -> {
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
