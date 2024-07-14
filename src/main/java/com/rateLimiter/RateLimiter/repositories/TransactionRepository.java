package com.rateLimiter.RateLimiter.repositories;

import com.rateLimiter.RateLimiter.entitiy.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity,Long> {

    @Query("SELECT t FROM TransactionEntity t WHERE t.userId = :userId")
    List<TransactionEntity> findAllByUserId(Long userId);
}
