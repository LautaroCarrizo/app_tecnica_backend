package com.app.backend.api.dtos.login;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank String dni,
        @NotBlank String password
) {}