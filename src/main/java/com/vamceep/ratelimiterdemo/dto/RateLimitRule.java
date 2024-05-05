package com.vamceep.ratelimiterdemo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RateLimitRule {
    int windowDurationSec;
    int maxRequestsInWindow;
}
