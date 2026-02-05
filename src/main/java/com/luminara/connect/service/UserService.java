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
    private final Map<String, String> userIdByPhone = new ConcurrentHashMap<>();

    public User createUser(String displayName, String phoneNumber) {
        if (userIdByPhone.containsKey(phoneNumber)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already registered");
        }
        User user = new User(displayName, phoneNumber);
        users.put(user.getId(), user);
        userIdByPhone.put(phoneNumber, user.getId());
        return user;
    }

    public User getById(String userId) {
        User user = users.get(userId);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found: " + userId);
        }
        return user;
    }

    public User findByPhone(String phoneNumber) {
        String userId = userIdByPhone.get(phoneNumber);
        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No account found for this phone number");
        }
        return getById(userId);
    }

    public User findByPhoneOrCreate(String phoneNumber, String displayName) {
        String existing = userIdByPhone.get(phoneNumber);
        if (existing != null) {
            return getById(existing);
        }
        if (displayName == null || displayName.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "displayName is required for first-time sign-up");
        }
        return createUser(displayName, phoneNumber);
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
