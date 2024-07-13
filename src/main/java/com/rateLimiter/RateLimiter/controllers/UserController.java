package com.rateLimiter.RateLimiter.controllers;



import com.rateLimiter.RateLimiter.dto.UserDTO;
import com.rateLimiter.RateLimiter.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/ratelimit")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{id}")
    public UserDTO getUserTransactionsByID(@PathVariable String id) {
    return userService.getUserTransactionsById(id);
    }

    @PostMapping(path = "/post/{id}")
    public UserDTO createUserTransaction(@PathVariable String id, @RequestBody UserDTO userDTO) {
        return userService.createUserTransaction(userDTO);
    }
    @DeleteMapping(path= "/delete/{id}")
    public boolean deleteUserTransaction(@PathVariable String id) {
    return userService.deleteUserTransaction(id);
    }
    @PutMapping(path = "/update/{id}")
    public boolean updateUserTransaction(@PathVariable String id, @RequestBody UserDTO userDTO) {
        return userService.updateUserTransaction(userDTO, id);
    }
}
