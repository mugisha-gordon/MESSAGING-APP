package com.luminara.connect.service;

import com.luminara.connect.model.AuthSession;
import com.luminara.connect.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AuthService {
    private final UserService userService;
    private final SecureRandom random = new SecureRandom();
    private final Map<String, String> otpByPhone = new ConcurrentHashMap<>();
    private final Map<String, AuthSession> sessionsByToken = new ConcurrentHashMap<>();

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public String requestCode(String phoneNumber, String displayName) {
        userService.findByPhoneOrCreate(phoneNumber, displayName);
        String otp = String.format("%06d", random.nextInt(1_000_000));
        otpByPhone.put(phoneNumber, otp);
        return otp;
    }

    public AuthSession verifyCode(String phoneNumber, String code) {
        String expected = otpByPhone.get(phoneNumber);
        if (expected == null || !expected.equals(code)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid verification code");
        }
        User user = userService.findByPhone(phoneNumber);
        String token = UUID.randomUUID().toString();
        AuthSession session = new AuthSession(token, user.getId(), Instant.now());
        sessionsByToken.put(token, session);
        otpByPhone.remove(phoneNumber);
        return session;
    }

    public User resolveUserByToken(String token) {
        AuthSession session = sessionsByToken.get(token);
        if (session == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid or expired session token");
        }
        return userService.getById(session.userId());
    }
}
