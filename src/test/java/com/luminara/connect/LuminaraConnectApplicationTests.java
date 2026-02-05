package com.luminara.connect;

import com.luminara.connect.model.Chat;
import com.luminara.connect.model.User;
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

    @Test
    void endToEndMessagingWalletAndGiftFlow() {
        User alice = userService.createUser("Alice", "+1000000001");
        User bob = userService.createUser("Bob", "+1000000002");

        userService.topUp(alice.getId(), new BigDecimal("30.00"));

        Chat chat = chatService.createChat(List.of(alice.getId(), bob.getId()));
        chatService.sendMessage(chat.getId(), alice.getId(), "Hey Bob 👋");
        chatService.sendMessage(chat.getId(), bob.getId(), "Hey Alice!");

        walletService.transfer(alice.getId(), bob.getId(), new BigDecimal("8.50"));
        walletService.sendGift(alice.getId(), bob.getId(), "ROSE");

        assertEquals(new BigDecimal("20.00"), alice.getWalletBalance());
        assertEquals(new BigDecimal("10.00"), bob.getWalletBalance());
        assertEquals(2, chat.getMessages().size());
    }
}
