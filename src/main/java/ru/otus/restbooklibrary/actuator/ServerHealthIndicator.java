package ru.otus.restbooklibrary.actuator;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
public class ServerHealthIndicator implements HealthIndicator {
    private final String serverUrl = "http://localhost:8080";
    private final RestTemplate restTemplate;

    public ServerHealthIndicator() {
        restTemplate = new RestTemplate();
    }

    @Override
    public Health health() {
        try {
            ResponseEntity<JsonNode> responseEntity = restTemplate.getForEntity(serverUrl, JsonNode.class);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return Health.up().withDetail("status", "OK").build();
            } else {
                return Health.down().build();
            }
        } catch (Exception e) {
            return Health.down().withException(e).build();
        }
    }
}
