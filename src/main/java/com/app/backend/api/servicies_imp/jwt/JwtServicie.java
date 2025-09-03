package com.app.backend.api.servicies_imp.jwt;


import com.app.backend.api.config.jwt.JwtProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtServicie {
    private final JwtProperties props;

    // 1) Construye la clave HMAC a partir del secreto en Base64
    private Key key() {
        byte[] secretBytes = Decoders.BASE64.decode(props.getSecretBase64());
        return Keys.hmacShaKeyFor(secretBytes); // HS256 requiere >= 256 bits
    }

    // 2) Emite un Access Token con claims mínimos para tu app
    public String issueAccess(Long userId, String dni, String role) {
        Instant now = Instant.now();
        Instant exp = now.plus(Duration.ofMinutes(props.getAccessTtlMin()));

        return Jwts.builder().setIssuer(props.getIssuer())                   // quién emite (tu app)
                .setAudience(props.getAudience())               // para quién
                .setSubject(String.valueOf(userId))             // sub = id local del user
                .setId(UUID.randomUUID().toString())            // jti = id único del token
                .setIssuedAt(Date.from(now))                    // iat
                .setNotBefore(Date.from(now.minusSeconds(5)))   // nbf (tolerancia)
                .setExpiration(Date.from(exp))                  // exp
                .claim("dni", dni)                              // dato útil para logs/servicios
                .claim("role", role)                            // USER / ADMIN (tu rol local)
                .signWith(key(), SignatureAlgorithm.HS256)      // firma HS256
                .compact();
    }

    // 3) Valida la firma y restricciones estándar; si algo falla, lanza JwtException
    public Jws<Claims> parseAndValidate(String token) throws JwtException {
        return Jwts.parser().requireIssuer(props.getIssuer()).requireAudience(props.getAudience()).setSigningKey(key()).build().parseClaimsJws(token);
    }


}
