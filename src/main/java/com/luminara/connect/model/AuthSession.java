package com.luminara.connect.model;

import java.time.Instant;

public record AuthSession(String token, String userId, Instant createdAt) {
}
