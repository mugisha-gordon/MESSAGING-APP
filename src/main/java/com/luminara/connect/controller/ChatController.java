package com.luminara.connect.controller;

import com.luminara.connect.dto.CreateChatRequest;
import com.luminara.connect.dto.SendMessageRequest;
import com.luminara.connect.model.Chat;
import com.luminara.connect.model.Message;
import com.luminara.connect.service.ChatService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/chats")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public Chat createChat(@Valid @RequestBody CreateChatRequest request) {
        return chatService.createChat(request.participantIds());
    }

    @GetMapping
    public Collection<Chat> listChats() {
        return chatService.listChats();
    }

    @GetMapping("/{chatId}")
    public Chat getChat(@PathVariable String chatId) {
        return chatService.getChat(chatId);
    }

    @PostMapping("/{chatId}/messages")
    public Message sendMessage(@PathVariable String chatId, @Valid @RequestBody SendMessageRequest request) {
        return chatService.sendMessage(chatId, request.senderId(), request.content());
    }
}
