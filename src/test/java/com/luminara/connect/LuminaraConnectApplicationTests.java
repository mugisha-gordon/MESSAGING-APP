package com.luminara.connect;

import com.luminara.connect.model.AuthSession;
import com.luminara.connect.model.Chat;
import com.luminara.connect.model.User;
import com.luminara.connect.service.AuthService;
import com.luminara.connect.service.ChatService;
import com.luminara.connect.service.UserService;
import com.luminara.connect.service.WalletService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LuminaraConnectApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private ChatService chatService;

    @Autowired
    private WalletService walletService;

    @Autowired
    private AuthService authService;

    @Test
    void endToEndMessagingWalletGiftAndAuthFlow() {
        String aliceOtp = authService.requestCode("+1000000001", "Alice");
        String bobOtp = authService.requestCode("+1000000002", "Bob");

        AuthSession aliceSession = authService.verifyCode("+1000000001", aliceOtp);
        AuthSession bobSession = authService.verifyCode("+1000000002", bobOtp);

        User alice = authService.resolveUserByToken(aliceSession.token());
        User bob = authService.resolveUserByToken(bobSession.token());

        userService.topUp(alice.getId(), new BigDecimal("30.00"));

        Chat chat = chatService.createChat(List.of(alice.getId(), bob.getId()));
        chatService.sendMessage(chat.getId(), alice.getId(), "Hey Bob 👋");
        chatService.sendMessage(chat.getId(), bob.getId(), "Hey Alice!");

        walletService.transfer(alice.getId(), bob.getId(), new BigDecimal("8.50"));
        walletService.sendGift(alice.getId(), bob.getId(), "ROSE");

        assertEquals(new BigDecimal("20.00"), alice.getWalletBalance());
        assertEquals(new BigDecimal("10.00"), bob.getWalletBalance());
        assertEquals(2, chat.getMessages().size());
        assertEquals(2, walletService.transactionsForUser(alice.getId()).size());
        assertEquals(2, walletService.transactionsForUser(bob.getId()).size());
    }
}
