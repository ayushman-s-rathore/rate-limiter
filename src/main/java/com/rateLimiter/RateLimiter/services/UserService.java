package com.rateLimiter.RateLimiter.services;


import com.rateLimiter.RateLimiter.dto.LoginDto;
import com.rateLimiter.RateLimiter.dto.UserDTO;
import com.rateLimiter.RateLimiter.entitiy.TransactionEntity;
import com.rateLimiter.RateLimiter.entitiy.UserEntity;
import com.rateLimiter.RateLimiter.exceptions.UserCreationException;
import com.rateLimiter.RateLimiter.repositories.TransactionRepository;
import com.rateLimiter.RateLimiter.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    final UserRepository userRepository;
    final TransactionRepository transactionRepository;
    final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, TransactionRepository transactionRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
        this.modelMapper = modelMapper;
    }

    public List<UserDTO> getUserTransactionsById(String userId) {
        Long userIdLong = Long.valueOf(userId);
        List<TransactionEntity> transactionEntities = transactionRepository.findAllByUserId(userIdLong);
        return transactionEntities.stream()
                .map(transactionEntity -> modelMapper.map(transactionEntity, UserDTO.class))
                .collect(Collectors.toList());
    }

    public LoginDto createUser(LoginDto loginDTO) {
        if (userRepository.existsByUsername(loginDTO.getUsername())) {
            throw new UserCreationException("A user with this username already exists");
        }

        try {
            UserEntity userEntity = modelMapper.map(loginDTO, UserEntity.class);
            userEntity = userRepository.save(userEntity);
            return modelMapper.map(userEntity, LoginDto.class);
        } catch (Exception e) {
            throw new UserCreationException("Error creating user: " + e.getMessage());
        }
    }
    public boolean deleteUserTransaction(String userId) {
        boolean isPresent = transactionRepository.existsById(Long.valueOf(userId));
        if (!isPresent) return false;
        transactionRepository.deleteById(Long.valueOf(userId));
        return true;
    }
    public boolean updateUserTransaction(UserDTO userDTO, String id) {
        Long userId = Long.valueOf(id);
        boolean isPresent = transactionRepository.existsById(userId);

        if (!isPresent) {
            return false;
        }

        TransactionEntity userEntity = modelMapper.map(userDTO, TransactionEntity.class);
        userEntity.setId(userId);
        transactionRepository.save(userEntity);
        return true;

    }
    public UserDTO createUserTransaction(UserDTO userDTO){
        TransactionEntity userEntity = modelMapper.map(userDTO, TransactionEntity.class);
        return modelMapper.map(transactionRepository.save(userEntity),UserDTO.class);
    }
}
