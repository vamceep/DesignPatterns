package com.vamceep.ratelimiterdemo.service;

public interface IRateLimiter {
    boolean allowRequest();
}
