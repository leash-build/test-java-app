package com.leash.testapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, String> home() {
        return Map.of("app", "test-java-app", "status", "running");
    }

    @GetMapping("/health")
    public Map<String, Boolean> health() {
        return Map.of("healthy", true);
    }
}
