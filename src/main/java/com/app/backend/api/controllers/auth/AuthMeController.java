package com.app.backend.api.controllers.auth;


import com.app.backend.api.dtos.login.MeResponse;
import com.app.backend.api.models.user.User;
import com.app.backend.api.repository.users.UserRepository;
import com.app.backend.api.services_imp.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.flywaydb.core.internal.util.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthMeController {

    private final UserRepository users;

    @GetMapping("/me")
    public ResponseEntity<MeResponse> me(org.springframework.security.core.Authentication auth) {
        // El filtro ya validó el JWT y dejó el principal en el SecurityContext
        String userId = (String) auth.getPrincipal(); // sub en el JWT (userId)
        User u = users.findById(Long.valueOf(userId))
                .orElseThrow(() -> new java.util.NoSuchElementException("Usuario no encontrado"));

        MeResponse resp = new MeResponse(
                u.getId(),
                u.getDni(),
                u.getName(),
                u.getEmail(),
                u.getRole().name(),
                u.getExternalEnabled(),
                u.getExternalBlocked()
        );
        return ResponseEntity.ok(resp);
    }
}