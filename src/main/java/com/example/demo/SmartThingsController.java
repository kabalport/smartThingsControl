package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/smartthings")
public class SmartThingsController {

    @Value("${smartthings.api.token}")
    private String apiToken;

    @Value("${smartthings.device.id}")
    private String deviceId;

    @Value("${aqara.hub.id}")
    private String aqaraHubId;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/plug/on")
    public ResponseEntity<String> turnPlugOn() {
        String url = String.format("https://api.smartthings.com/v1/devices/%s/commands", deviceId);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiToken);
        headers.set("Content-Type", "application/json");

        String body = "{\"commands\": [{\"component\": \"main\", \"capability\": \"switch\", \"command\": \"on\"}]}";
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        return restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
    }

    @PostMapping("/plug/off")
    public ResponseEntity<String> turnPlugOff() {
        String url = String.format("https://api.smartthings.com/v1/devices/%s/commands", deviceId);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiToken);
        headers.set("Content-Type", "application/json");

        String body = "{\"commands\": [{\"component\": \"main\", \"capability\": \"switch\", \"command\": \"off\"}]}";
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        return restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
    }

    @GetMapping("/devices")
    public ResponseEntity<String> getDevices() {
        String url = "https://api.smartthings.com/v1/devices";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
    }

    @GetMapping("/plug/status")
    public ResponseEntity<String> getPlugStatus() {
        String url = String.format("https://api.smartthings.com/v1/devices/%s/status", deviceId);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
    }

    @GetMapping("/plug/energy")
    public ResponseEntity<String> getPlugEnergyUsage() {
        String url = String.format("https://api.smartthings.com/v1/devices/%s/status", deviceId);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        // 에너지 사용량 정보 파싱
        return ResponseEntity.ok(response.getBody());
    }


    @PostMapping("/hub/restart")
    public ResponseEntity<String> restartHub() {
        String url = String.format("https://api.smartthings.com/v1/devices/%s/commands", aqaraHubId);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiToken);
        headers.set("Content-Type", "application/json");

        String body = "{\"commands\": [{\"component\": \"main\", \"capability\": \"restart\", \"command\": \"restart\"}]}";
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        return restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
    }
}
