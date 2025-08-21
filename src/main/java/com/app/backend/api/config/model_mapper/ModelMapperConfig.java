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
import com.app.backend.api.models.user.User;
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

        // Config global: usá el nombre totalmente calificado para evitar choque con @Configuration de Spring
        mm.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT);

        // ---- Converters ----

        // Equipo -> EquipoRefDTO
        Converter<Equipo, EquipoRefDTO> equipoToRef = ctx -> {
            Equipo e = ctx.getSource();
            if (e == null) return null;
            EquipoRefDTO ref = new EquipoRefDTO();
            ref.setId(e.getId());
            ref.setEstado(e.getEstado());
            String type = e.getClass().getSimpleName().toUpperCase();
            // Si tu DTO usa 'type':
            ref.setTipo(type);
            // Si tu DTO usa 'tipo', usa esta línea en su lugar y borra la de arriba:
            // ref.setTipo(type);
            ref.setLabel(type + " #" + e.getId());
            return ref;
        };

        // User -> UserRefDTO
        Converter<User, UserRefDTO> userToRef = ctx -> {
            User u = ctx.getSource();
            if (u == null) return null;
            UserRefDTO ref = new UserRefDTO();
            ref.setId(u.getId());
            ref.setName(u.getName());
            ref.setEmail(u.getEmail());
            return ref;
        };

        // ---- TypeMaps ----

        // OrderItem -> OrderItemDetailDTO (inyecta equipo ref)
        mm.typeMap(OrderItem.class, OrderItemDetailDTO.class)
                .addMappings(m -> m.using(equipoToRef)
                        .map(OrderItem::getEquipment, OrderItemDetailDTO::setEquipment));

        // Order -> OrderDetailDTO
        mm.typeMap(Order.class, OrderDetailDTO.class)
                .addMappings(m -> {
                    // user.id -> userId (nombre distinto)
                    m.map(src -> (src.getUser() != null ? src.getUser().getId() : null),
                            OrderDetailDTO::setUserId);
                    // items se arma en postConverter para aplicar el typeMap de cada item
                    m.skip(OrderDetailDTO::setItems);
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

        // TransferenciaItem -> TransferenciaItemDetailDTO (inyecta equipo ref)
        mm.typeMap(TransferenciaItem.class, TransferenciaItemDetailDTO.class)
                .addMappings(m -> m.using(equipoToRef)
                        .map(TransferenciaItem::getEquipment, TransferenciaItemDetailDTO::setEquipment));

        // Transferencia -> TransferenciaDetailDTO
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
