package com.rateLimiter.RateLimiter.configuration;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class RateLimiterConfig {

    @Bean
    public Map<String, Bucket> rateLimiterBuckets() {
        Map<String, Bucket> rateLimiters = new HashMap<>();

        Bandwidth limitGetUserTransactions = Bandwidth.classic(5, Refill.intervally(5, Duration.ofMinutes(1)));
        rateLimiters.put("getUserTransactionsByID", Bucket4j.builder().addLimit(limitGetUserTransactions).build());

        Bandwidth limitCreateUserTransaction = Bandwidth.classic(10, Refill.intervally(10, Duration.ofMinutes(1)));
        rateLimiters.put("createUserTransaction", Bucket4j.builder().addLimit(limitCreateUserTransaction).build());

        Bandwidth limitDeleteUserTransaction = Bandwidth.classic(5, Refill.intervally(5, Duration.ofMinutes(1)));
        rateLimiters.put("deleteUserTransaction", Bucket4j.builder().addLimit(limitDeleteUserTransaction).build());

        Bandwidth limitUpdateUserTransaction = Bandwidth.classic(10, Refill.intervally(10, Duration.ofMinutes(1)));
        rateLimiters.put("updateUserTransaction", Bucket4j.builder().addLimit(limitUpdateUserTransaction).build());

        return rateLimiters;
    }
}
