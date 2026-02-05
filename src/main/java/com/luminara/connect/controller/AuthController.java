package com.luminara.connect.controller;

import com.luminara.connect.dto.AuthCodeRequest;
import com.luminara.connect.dto.VerifyCodeRequest;
import com.luminara.connect.model.AuthSession;
import com.luminara.connect.model.User;
import com.luminara.connect.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/request-code")
    public Map<String, String> requestCode(@Valid @RequestBody AuthCodeRequest request) {
        String code = authService.requestCode(request.phoneNumber(), request.displayName());
        return Map.of(
                "message", "Verification code generated",
                "demoCode", code
        );
    }

    @PostMapping("/verify-code")
    public Map<String, Object> verifyCode(@Valid @RequestBody VerifyCodeRequest request) {
        AuthSession session = authService.verifyCode(request.phoneNumber(), request.code());
        return Map.of(
                "token", session.token(),
                "userId", session.userId(),
                "createdAt", session.createdAt()
        );
    }

    @GetMapping("/me")
    public User me(@RequestParam String token) {
        return authService.resolveUserByToken(token);
    }
}
