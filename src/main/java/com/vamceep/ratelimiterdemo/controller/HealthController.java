package com.vamceep.ratelimiter.Ratelimiter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HealthController {
    @GetMapping(path = "/ping")
    public ResponseEntity<?> ping() {
        return ResponseEntity.ok().body("Service is up.");
    }
}
