package com.luminara.connect.service;

import com.luminara.connect.model.Chat;
import com.luminara.connect.model.Message;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ChatService {

    private final Map<String, Chat> chats = new ConcurrentHashMap<>();
    private final UserService userService;

    public ChatService(UserService userService) {
        this.userService = userService;
    }

    public Chat createChat(java.util.List<String> participants) {
        participants.forEach(userService::getById);
        Chat chat = new Chat(participants);
        chats.put(chat.getId(), chat);
        return chat;
    }

    public Chat getChat(String chatId) {
        Chat chat = chats.get(chatId);
        if (chat == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Chat not found: " + chatId);
        }
        return chat;
    }

    public Collection<Chat> listChats() {
        return chats.values();
    }

    public Message sendMessage(String chatId, String senderId, String content) {
        Chat chat = getChat(chatId);
        userService.getById(senderId);
        if (!chat.getParticipantIds().contains(senderId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sender must be a participant in the chat");
        }
        Message message = new Message(chatId, senderId, content);
        chat.getMessages().add(message);
        return message;
    }
}
