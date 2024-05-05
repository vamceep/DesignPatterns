package com.vamceep.ratelimiterdemo.service;

import com.vamceep.ratelimiterdemo.dto.RateLimitRule;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class IdentificationService {
    private final Map<String, IRateLimiter> rateLimiterMap = new HashMap<>();
    public boolean isValidRequest(String clientId) {
        if(!rateLimiterMap.containsKey(clientId)) {
            createNewClient(clientId);
        }
        return rateLimiterMap.get(clientId).allowRequest();
    }

    private void createNewClient(String clientId) {
        RateLimitRule rule = RateLimitRule.builder()
                .maxRequestsInWindow(1)
                .windowDurationSec(2)
                .build();
        IRateLimiter rateLimiter = new SlidingWindowRateLimiter(rule);
        rateLimiterMap.put(clientId, rateLimiter);
    }
}
