package com.luminara.connect.service;

import com.luminara.connect.model.GiftType;
import com.luminara.connect.model.User;
import com.luminara.connect.model.WalletTransaction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class WalletService {

    private final UserService userService;
    private final List<GiftType> giftCatalog = List.of(
            new GiftType("ROSE", "Digital Rose", new BigDecimal("1.50")),
            new GiftType("CHOCOLATE", "Luxury Chocolate Box", new BigDecimal("5.00")),
            new GiftType("STAR", "Golden Star", new BigDecimal("10.00"))
    );
    private final List<WalletTransaction> transactions = new CopyOnWriteArrayList<>();

    public WalletService(UserService userService) {
        this.userService = userService;
    }

    public User transfer(String fromUserId, String toUserId, BigDecimal amount) {
        User sender = userService.getById(fromUserId);
        User receiver = userService.getById(toUserId);
        ensureBalance(sender, amount);
        sender.setWalletBalance(sender.getWalletBalance().subtract(amount));
        receiver.setWalletBalance(receiver.getWalletBalance().add(amount));
        transactions.add(new WalletTransaction("TRANSFER", fromUserId, toUserId, amount, "Peer transfer"));
        return sender;
    }

    public List<GiftType> giftCatalog() {
        return giftCatalog;
    }

    public User sendGift(String fromUserId, String toUserId, String giftCode) {
        User sender = userService.getById(fromUserId);
        User receiver = userService.getById(toUserId);
        GiftType gift = giftCatalog.stream()
                .filter(it -> it.code().equalsIgnoreCase(giftCode))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unknown gift code: " + giftCode));
        ensureBalance(sender, gift.price());
        sender.setWalletBalance(sender.getWalletBalance().subtract(gift.price()));
        receiver.setWalletBalance(receiver.getWalletBalance().add(gift.price()));
        transactions.add(new WalletTransaction("GIFT", fromUserId, toUserId, gift.price(), "Gift: " + gift.title()));
        return sender;
    }

    public List<WalletTransaction> transactionsForUser(String userId) {
        userService.getById(userId);
        return transactions.stream()
                .filter(tx -> userId.equals(tx.getFromUserId()) || userId.equals(tx.getToUserId()))
                .toList();
    }

    private void ensureBalance(User sender, BigDecimal amount) {
        if (sender.getWalletBalance().compareTo(amount) < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient wallet balance");
        }
    }
}
