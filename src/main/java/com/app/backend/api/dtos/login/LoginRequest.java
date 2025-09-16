package com.app.backend.api.dtos.login;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank
        @JsonProperty("dni")
        @JsonAlias({"username"})
        String dni,

        @NotBlank
        String password
) {}