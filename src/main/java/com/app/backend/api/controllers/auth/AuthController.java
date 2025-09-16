package com.app.backend.api.controllers.auth;

import com.app.backend.api.servicies_imp.auth.AuthService;
import com.app.backend.api.dtos.login.LoginRequest;
import com.app.backend.api.dtos.login.TokenResponseJwt;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseJwt> login(@Valid @RequestBody LoginRequest req) {
        TokenResponseJwt tokens = authService.login(req.dni(), req.password());
        return ResponseEntity.ok(tokens);
    }
}
