package com.app.backend.api.config.jwt.jsonAuth;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.time.OffsetDateTime;
import java.util.Map;

public class JsonAccessDeniedHandler implements AccessDeniedHandler {
    private final ObjectMapper om = new ObjectMapper();


    public void handle(HttpServletRequest req, HttpServletResponse response, AccessDeniedException ex) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        var body = Map.of("timestamp", OffsetDateTime.now().toString(), "status", 403, "error", "Forbidden", "message", ex.getMessage(), "path", req.getRequestURI());
        try {
            om.writeValue(response.getOutputStream(), body);
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }

    }
}