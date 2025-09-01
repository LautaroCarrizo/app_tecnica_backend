package com.app.backend.api.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.jwt")
@Getter
@Setter
public class JwtProperties {
    private String issuer;
    private String audience;
    private String secretBase64;
    private int accessTtlMin;
    private int clockSkewSeconds;
}