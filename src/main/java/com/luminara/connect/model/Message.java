package com.luminara.connect.model;

import java.time.Instant;
import java.util.UUID;

public class Message {
    private final String id;
    private final String chatId;
    private final String senderId;
    private final String content;
    private final Instant sentAt;

    public Message(String chatId, String senderId, String content) {
        this.id = UUID.randomUUID().toString();
        this.chatId = chatId;
        this.senderId = senderId;
        this.content = content;
        this.sentAt = Instant.now();
    }

    public String getId() {
        return id;
    }

    public String getChatId() {
        return chatId;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getContent() {
        return content;
    }

    public Instant getSentAt() {
        return sentAt;
    }
}
