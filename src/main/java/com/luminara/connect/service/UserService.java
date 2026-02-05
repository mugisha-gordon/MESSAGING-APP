package com.luminara.connect.service;

import com.luminara.connect.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {

    private final Map<String, User> users = new ConcurrentHashMap<>();

    public User createUser(String displayName, String phoneNumber) {
        User user = new User(displayName, phoneNumber);
        users.put(user.getId(), user);
        return user;
    }

    public User getById(String userId) {
        User user = users.get(userId);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found: " + userId);
        }
        return user;
    }

    public Collection<User> listUsers() {
        return users.values();
    }

    public User topUp(String userId, BigDecimal amount) {
        User user = getById(userId);
        user.setWalletBalance(user.getWalletBalance().add(amount));
        return user;
    }
}
