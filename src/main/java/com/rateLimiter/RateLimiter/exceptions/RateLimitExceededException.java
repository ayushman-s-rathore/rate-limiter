package com.rateLimiter.RateLimiter.exceptions;

public class RateLimitExceededException extends RuntimeException{
    public RateLimitExceededException(String message) {
        super(message);
    }
}
