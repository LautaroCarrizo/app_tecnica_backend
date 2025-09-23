package com.app.backend.api.config.jwt.authFilterJwt;

import com.app.backend.api.config.jwt.jsonAuth.JsonAuthEntryPoint;
import com.app.backend.api.services_imp.jwt.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String BEARER = "Bearer ";
    private final JwtService jwtService;
    private final JsonAuthEntryPoint entryPoint;
    private final AntPathMatcher matcher = new AntPathMatcher();



    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String p = request.getRequestURI();
        return matcher.match("/auth/login", p)
                || matcher.match("/swagger-ui/**", p)
                || matcher.match("/v3/api-docs/**", p)
                || matcher.match("/swagger-ui.html", p);
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest req,
                                    @NonNull HttpServletResponse res,
                                    @NonNull FilterChain chain) throws ServletException, IOException {

        String h = req.getHeader(HttpHeaders.AUTHORIZATION);
        if (h == null || !h.startsWith(BEARER)) {
            chain.doFilter(req, res);
            return;
        }

        String token = h.substring(BEARER.length()).trim();
        try {

            Claims claims = jwtService.parseAndValidate(token); // firma, iss, aud, exp
            String role = (String) claims.get("role");
            var authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));

            AbstractAuthenticationToken auth = new AbstractAuthenticationToken(authorities) {
                @Override
                public Object getCredentials() {
                    return token;
                }

                @Override
                public Object getPrincipal() {
                    return claims.getSubject();
                } // sub=userId
            };
            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
            auth.setAuthenticated(true);

            SecurityContextHolder.getContext().setAuthentication(auth);
            chain.doFilter(req, res);

        } catch (JwtException ex) {
            SecurityContextHolder.clearContext();
            entryPoint.commence(req, res, ex); // 401 JSON
        }
    }
}