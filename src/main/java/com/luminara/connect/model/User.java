package com.luminara.connect.model;

import java.math.BigDecimal;
import java.util.UUID;

public class User {
    private final String id;
    private String displayName;
    private String phoneNumber;
    private BigDecimal walletBalance;

    public User(String displayName, String phoneNumber) {
        this.id = UUID.randomUUID().toString();
        this.displayName = displayName;
        this.phoneNumber = phoneNumber;
        this.walletBalance = BigDecimal.ZERO;
    }

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public BigDecimal getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(BigDecimal walletBalance) {
        this.walletBalance = walletBalance;
    }
}
