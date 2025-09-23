package com.app.backend.api.config.jwt.externalAuth;

import com.app.backend.api.dtos.login.ExternalAuthResponse;
import com.app.backend.api.dtos.login.LoggerUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

/**
 * Cliente HTTP para los endpoints de PaseShow.
 * - authenticate(dni, password) --> token externo
 * - fetchLoggerUser(token)      --> perfil de usuario externo
 * <p>
 * NOTA: Este cliente no guarda estado ni persiste nada; solo hace las llamadas HTTP.
 */
@Component
@RequiredArgsConstructor
public class PaseShowAuthClient {

    private final WebClient paseShowWebClient;
    private final PaseShowProperties props;

    /**
     * POST /auth  (dni + password) -> ExternalAuthResponse{ token }
     * Lanza BadCredentialsException en 4xx y IllegalStateException en 5xx.
     */
    public String authenticate(String username, String password) {
        ExternalAuthResponse r = paseShowWebClient.post()
                .uri(props.getAuthPath()) // "/auth"
                .contentType(MediaType.APPLICATION_JSON)       // <--- JSON
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(Map.of("username", username, "password", password))
                .retrieve()
                .onStatus(s -> s.value() == HttpStatus.UNAUTHORIZED.value(), res ->
                        res.bodyToMono(String.class).map(body ->
                                new BadCredentialsException("Auth externo 401: " + body)))
                .onStatus(HttpStatusCode::isError, res ->
                        res.bodyToMono(String.class).map(body ->
                                new IllegalStateException("Auth externo error: " + body)))
                .bodyToMono(ExternalAuthResponse.class)
                .block();

        if (r == null || r.token() == null || r.token().isBlank()) {
            throw new IllegalStateException("No se recibió token de PaseShow");
        }
        return r.token();
    }


    /**
     * GET /auth/loggedUser  (Bearer token externo) -> LoggerUserResponse
     * Lanza IllegalStateException si el externo responde error.
     */
    public LoggerUserResponse fetchLoggerUser(String externalToken) {
        LoggerUserResponse profile = paseShowWebClient.get()
                .uri(props.getLoggerUserPath())
                .header("Authorization", "Bearer " + externalToken)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::isError, res ->
                        res.bodyToMono(String.class).map(body ->
                                new IllegalStateException("loggerUser error: " + body)))
                .bodyToMono(LoggerUserResponse.class)
                .block();

        if (profile == null || profile.dni() == null || profile.dni().isBlank()) {
            throw new IllegalStateException("Perfil inválido de loggerUser");
        }
        return profile;
    }
}
