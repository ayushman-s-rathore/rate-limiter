package com.rateLimiter.RateLimiter.aspect;


import com.rateLimiter.RateLimiter.controllers.UserController;
import com.rateLimiter.RateLimiter.exceptions.RateLimitExceededException;
import io.github.bucket4j.Bucket;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Aspect
@Component
public class RateLimitingAspect {
    @Autowired
    private Map<String, Bucket> rateLimiterBuckets;

    @Before("execution(* com.rateLimiter.RateLimiter.controllers.UserController.getUserTransactionsByID(..))")
    public void rateLimitGetUserTransactionsByID() {
        rateLimit("getUserTransactionsByID");
    }

    @Before("execution(* com.rateLimiter.RateLimiter.controllers.UserController.createUserTransaction(..))")
    public void rateLimitCreateUserTransaction() {
        rateLimit("createUserTransaction");
    }

    @Before("execution(* com.rateLimiter.RateLimiter.controllers.UserController.deleteUserTransaction(..))")
    public void rateLimitDeleteUserTransaction() {
        rateLimit("deleteUserTransaction");
    }


    @Before("execution(* com.rateLimiter.RateLimiter.controllers.UserController.updateUserTransaction(..))")
    public void rateLimitUpdateUserTransaction() {
        rateLimit("updateUserTransaction");
    }

    private void rateLimit(String apiName) {
        Bucket bucket = rateLimiterBuckets.get(apiName);
        if (bucket != null && !bucket.tryConsume(1)) {
            throw new RateLimitExceededException("Rate limit exceeded for " + apiName + ". Allowed calls per minute exceeded.");
        }
    }
}
