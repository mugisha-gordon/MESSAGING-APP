package com.luminara.connect.controller;

import com.luminara.connect.dto.SendGiftRequest;
import com.luminara.connect.dto.TransferRequest;
import com.luminara.connect.model.GiftType;
import com.luminara.connect.model.User;
import com.luminara.connect.model.WalletTransaction;
import com.luminara.connect.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/transfer")
    public User transfer(@Valid @RequestBody TransferRequest request) {
        return walletService.transfer(request.fromUserId(), request.toUserId(), request.amount());
    }

    @GetMapping("/gifts")
    public List<GiftType> giftCatalog() {
        return walletService.giftCatalog();
    }

    @PostMapping("/gifts/send")
    public User sendGift(@Valid @RequestBody SendGiftRequest request) {
        return walletService.sendGift(request.fromUserId(), request.toUserId(), request.giftCode());
    }

    @GetMapping("/transactions/{userId}")
    public List<WalletTransaction> userTransactions(@PathVariable String userId) {
        return walletService.transactionsForUser(userId);
    }
}
