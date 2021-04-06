package ru.otus.restbooklibrary.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.otus.restbooklibrary.actuator.ServerHealthIndicator;

@Configuration
public class AppConfig {
    @Bean
    public ServerHealthIndicator server() {
        return new ServerHealthIndicator(new RestTemplate());
    }
}
