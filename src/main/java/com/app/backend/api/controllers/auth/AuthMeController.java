package com.app.backend.api.controllers.auth;


import com.app.backend.api.dtos.login.MeResponse;
import com.app.backend.api.models.user.User;
import com.app.backend.api.repository.users.UserRepository;
import com.app.backend.api.servicies_imp.jwt.JwtServicie;
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
    private final JwtServicie jwt;
    private final UserRepository users;

    @GetMapping("/me")
    public ResponseEntity<MeResponse> me(@RequestHeader(name = "Authorization", required = false) String authHeader) {
        // 1) Extraer el Bearer token
        if (!StringUtils.hasText(authHeader) || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).build();
        }
        String token = authHeader.substring("Bearer ".length()).trim();

        // 2) Validar/parsing del JWT
        var claims = jwt.parseAndValidate(token); // usa tu JwtService (el método que tengas para parsear/validar)
        Long userId = Long.valueOf(claims.getSubject()); // sub = userId (como definimos al emitir)


        // 3) Cargar usuario local
        User u = users.findById(userId).orElseThrow(() ->
                new NoSuchElementException("Usuario no encontrado"));

        // 4) Armar respuesta
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
