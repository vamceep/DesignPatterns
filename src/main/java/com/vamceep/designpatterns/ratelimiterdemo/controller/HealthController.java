package com.vamceep.designpatterns.ratelimiterdemo.controller;

import com.vamceep.designpatterns.ratelimiterdemo.service.IdentificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
public class HealthController {
    private final IdentificationService identificationService;

    public HealthController(IdentificationService identificationService) {
        this.identificationService = identificationService;
    }

    @GetMapping(path = "/ping")
    public ResponseEntity<?> ping(@RequestHeader(name = "client_id") String clientId) {
        if(identificationService.isValidRequest(clientId)) {
            return ResponseEntity.ok().body("Service is up.");
        }
        else {
            return ResponseEntity.status(429).build();
        }
    }
}
