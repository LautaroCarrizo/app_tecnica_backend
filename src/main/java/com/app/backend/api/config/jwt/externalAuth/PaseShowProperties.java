package com.app.backend.api.config.jwt.externalAuth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "paseshow")
@Getter
@Setter
public class PaseShowProperties {
    private String baseUrl;
    private String authPath;
    private String loggerUserPath;
    private int timeoutSeconds = 8;
}