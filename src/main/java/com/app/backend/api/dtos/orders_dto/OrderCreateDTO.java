package com.app.backend.api.dtos.orders_dto;

import com.app.backend.api.models.enums.OrderDestino;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderCreateDTO {
    @NotBlank
    @Size(max = 40)
    private String responsibility;
    @NotBlank
    @Size(max = 60)
    private String event;
    @Size(max = 60)
    private String notes;
    @NotNull
    private Long userId;
    @NotNull
    private OrderDestino destination;
    @Size(max = 80)
    private String externalReceiverName;
    @Size(max = 20)
    private String externalReceiverDni;
    @NotEmpty
    private List<@NotNull Long> equipmentIds;
}