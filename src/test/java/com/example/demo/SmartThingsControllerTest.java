package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SmartThingsControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testTurnPlugOn() {
        String url = "http://localhost:" + port + "/smartthings/plug/on";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer YOUR_SMARTTHINGS_API_TOKEN");
        headers.set("Content-Type", "application/json");

        String body = "{\"commands\": [{\"component\": \"main\", \"capability\": \"switch\", \"command\": \"on\"}]}";
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        System.out.println("Plug On Response: " + response.getBody());
    }

    @Test
    public void testTurnPlugOff() {
        String url = "http://localhost:" + port + "/smartthings/plug/off";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer YOUR_SMARTTHINGS_API_TOKEN");
        headers.set("Content-Type", "application/json");

        String body = "{\"commands\": [{\"component\": \"main\", \"capability\": \"switch\", \"command\": \"off\"}]}";
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        System.out.println("Plug Off Response: " + response.getBody());
    }

    @Test
    public void testGetDevices() {
        String url = "http://localhost:" + port + "/smartthings/devices";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer YOUR_SMARTTHINGS_API_TOKEN");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        System.out.println("Get Devices Response: " + response.getBody());
    }
}
