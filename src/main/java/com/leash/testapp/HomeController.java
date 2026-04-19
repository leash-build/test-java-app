package com.leash.testapp;

import build.leash.sdk.LeashAuth;
import build.leash.sdk.LeashError;
import build.leash.sdk.LeashUser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/me")
    public ResponseEntity<?> me(HttpServletRequest request) {
        try {
            LeashUser user = LeashAuth.getUser(request.getHeader("Cookie"));
            return ResponseEntity.ok(Map.of(
                    "id", user.getId(),
                    "email", user.getEmail(),
                    "name", user.getName(),
                    "picture", user.getPicture()
            ));
        } catch (LeashError e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/auth-status")
    public Map<String, Boolean> authStatus(HttpServletRequest request) {
        boolean authenticated = LeashAuth.isAuthenticated(request.getHeader("Cookie"));
        return Map.of("authenticated", authenticated);
    }
}
