package com.luminara.connect.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class WalletTransaction {
    private final String id;
    private final String type;
    private final String fromUserId;
    private final String toUserId;
    private final BigDecimal amount;
    private final String description;
    private final Instant createdAt;

    public WalletTransaction(String type, String fromUserId, String toUserId, BigDecimal amount, String description) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.amount = amount;
        this.description = description;
        this.createdAt = Instant.now();
    }

    public String getId() { return id; }

    public String getType() { return type; }

    public String getFromUserId() { return fromUserId; }

    public String getToUserId() { return toUserId; }

    public BigDecimal getAmount() { return amount; }

    public String getDescription() { return description; }

    public Instant getCreatedAt() { return createdAt; }
}
