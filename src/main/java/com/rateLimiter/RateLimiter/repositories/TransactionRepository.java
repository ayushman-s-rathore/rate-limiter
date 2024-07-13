package com.rateLimiter.RateLimiter.repositories;

import com.rateLimiter.RateLimiter.entitiy.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity,Long> {
    
}
