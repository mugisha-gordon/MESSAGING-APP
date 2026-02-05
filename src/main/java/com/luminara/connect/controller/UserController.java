package com.luminara.connect.controller;

import com.luminara.connect.dto.CreateUserRequest;
import com.luminara.connect.dto.TopUpRequest;
import com.luminara.connect.model.User;
import com.luminara.connect.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@Valid @RequestBody CreateUserRequest request) {
        return userService.createUser(request.displayName(), request.phoneNumber());
    }

    @GetMapping
    public Collection<User> listUsers() {
        return userService.listUsers();
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable String userId) {
        return userService.getById(userId);
    }

    @PostMapping("/wallet/top-up")
    public User topUp(@Valid @RequestBody TopUpRequest request) {
        return userService.topUp(request.userId(), request.amount());
    }
}
