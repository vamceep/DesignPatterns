package com.vamceep.ratelimiterdemo.service;

import com.vamceep.ratelimiterdemo.dto.RateLimitRule;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SlidingWindowRateLimiter implements  IRateLimiter{
    private final Queue<Long> queue;
    private final RateLimitRule rateLimitRule;

    public SlidingWindowRateLimiter(RateLimitRule rateLimitRule) {
        this.rateLimitRule = rateLimitRule;
        this.queue = new ConcurrentLinkedQueue<>();
    }

    @Override
    public boolean allowRequest() {
        checkAndUpdateQueue();
        if(queue.size() < rateLimitRule.getMaxRequestsInWindow()) {
            queue.offer(System.currentTimeMillis());
            return true;
        }
        return false;
    }

    private void checkAndUpdateQueue() {
        if(queue.isEmpty()) {
            return;
        }
        long currentTimeStamp = System.currentTimeMillis();
        long timeDuration =  (currentTimeStamp - queue.peek())/1000;
        while(timeDuration > rateLimitRule.getWindowDurationSec()) {
            queue.poll();
            if(!queue.isEmpty()){
                timeDuration =  (currentTimeStamp - queue.peek())/1000;
            }
            else {
                break;
            }
        }
    }
}
