package com.app.backend.api.dtos.login;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LoggerUserResponse(
        Long id,
        String nombre,
        String dni,
        String email,
        Boolean habilitado,
        Boolean bloqueado,
        Map<String, Boolean> roles
) {}