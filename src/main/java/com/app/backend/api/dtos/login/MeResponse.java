package com.app.backend.api.dtos.login;

public record MeResponse(
        Long id,
        String dni,
        String name,
        String email,
        String role,
        Boolean externalEnabled,
        Boolean externalBlocked
) {
}
