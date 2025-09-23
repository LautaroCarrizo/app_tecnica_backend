package com.app.backend.api.config.jwt.jsonAuth;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.OffsetDateTime;
import java.util.Map;

public class JsonAuthEntryPoint {
    private final ObjectMapper om = new ObjectMapper();

    public void commence(HttpServletRequest request, HttpServletResponse response, Exception ex) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        var body = Map.of(
                "timestamp", OffsetDateTime.now().toString(),
                "status", 401,
                "error", "Unauthorized",
                "message", ex.getMessage(),
                "path", request.getRequestURI()
        );
        try {
            om.writeValue(response.getOutputStream(), body);
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }
}
