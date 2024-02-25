package com.vamceep.designpatterns.ratelimiterdemo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RateLimitRule {
    int windowDurationSec;
    int maxRequestsInWindow;
}
