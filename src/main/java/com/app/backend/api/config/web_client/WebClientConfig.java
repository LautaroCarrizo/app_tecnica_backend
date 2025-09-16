package com.app.backend.api.config.web_client;

import com.app.backend.api.config.jwt.externalAuth.PaseShowProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;


@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

    private final PaseShowProperties props;

    @Bean
    public WebClient paseShowWebClient() {
        HttpClient httpClient = HttpClient.create().responseTimeout(Duration.ofSeconds(props.getTimeoutSeconds()));

        ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs( c -> c.defaultCodecs().maxInMemorySize(2 * 1024 * 1024))
                .build();

        // 3. Construcción del WebClient
        return WebClient.builder()
                .baseUrl(props.getBaseUrl()) //
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .exchangeStrategies(strategies)
                .build();
    }

}
