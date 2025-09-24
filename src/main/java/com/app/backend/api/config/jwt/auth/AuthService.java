package com.app.backend.api.config.jwt.auth;

import com.app.backend.api.config.jwt.externalAuth.PaseShowAuthClient;
import com.app.backend.api.dtos.login.LoggerUserResponse;
import com.app.backend.api.models.enums.RolUser;
import com.app.backend.api.models.user.User;
import com.app.backend.api.repository.users.UserRepository;
import com.app.backend.api.dtos.login.TokenResponseJwt;
import com.app.backend.api.config.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PaseShowAuthClient external;
    private final UserRepository users;
    private final JwtService jwt;

    @Transactional
    public TokenResponseJwt login(String dni, String password) {
        // 1) Autenticación externa (dni/pwd → token externo)
        final String externalToken;
        try {
            externalToken = external.authenticate(dni, password);
        } catch (BadCredentialsException e) {
            throw e; // 401 credenciales inválidas
        } catch (Exception e) {
            throw new IllegalStateException("Fallo autenticando contra PaseShow", e);
        }

        // 2) Perfil externo
        LoggerUserResponse profile = external.fetchLoggerUser(externalToken);
        if (Boolean.FALSE.equals(profile.habilitado()) || Boolean.TRUE.equals(profile.bloqueado())) {
            throw new BadCredentialsException("Usuario no habilitado o bloqueado en PaseShow");
        }

        // 3) Upsert local por DNI
        User user = users.findByDni(profile.dni())
                .orElseGet(() -> users.save(User.builder()
                        .dni(profile.dni())
                        .name(profile.nombre() != null ? profile.nombre().trim() : "Pendiente")
                        .email(profile.email())
                        .externalUserId(profile.id())
                        .externalEnabled(profile.habilitado())
                        .externalBlocked(profile.bloqueado())
                        .role(RolUser.USER)
                        .build()));

        boolean dirty = false;
        if (!Objects.equals(user.getName(), profile.nombre())) {
            user.setName(profile.nombre());
            dirty = true;
        }
        if (!Objects.equals(user.getEmail(), profile.email())) {
            user.setEmail(profile.email());
            dirty = true;
        }
        if (!Objects.equals(user.getExternalUserId(), profile.id())) {
            user.setExternalUserId(profile.id());
            dirty = true;
        }
        if (!Objects.equals(user.getExternalEnabled(), profile.habilitado())) {
            user.setExternalEnabled(profile.habilitado());
            dirty = true;
        }
        if (!Objects.equals(user.getExternalBlocked(), profile.bloqueado())) {
            user.setExternalBlocked(profile.bloqueado());
            dirty = true;
        }
        if (dirty) users.save(user);

        // 4) Emitir TU JWT (access token)
        String access = jwt.issueAccess(user.getId(), user.getDni(), user.getRole().name());
        return new TokenResponseJwt(access);
    }
}