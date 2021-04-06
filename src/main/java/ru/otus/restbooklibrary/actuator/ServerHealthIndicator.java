package ru.otus.restbooklibrary.actuator;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ServerHealthIndicator implements HealthIndicator {
    private final String serverUrl = "http://localhost:8080";
    private final RestTemplate restTemplate;

    public ServerHealthIndicator(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
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
