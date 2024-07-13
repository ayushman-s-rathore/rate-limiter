package com.rateLimiter.RateLimiter.repositories;



import com.rateLimiter.RateLimiter.entitiy.TransactionEntity;
import com.rateLimiter.RateLimiter.entitiy.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
  boolean existsByUsername(String username);
}
