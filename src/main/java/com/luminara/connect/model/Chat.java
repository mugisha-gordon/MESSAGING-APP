package com.luminara.connect.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Chat {
    private final String id;
    private final List<String> participantIds;
    private final List<Message> messages;

    public Chat(List<String> participantIds) {
        this.id = UUID.randomUUID().toString();
        this.participantIds = List.copyOf(participantIds);
        this.messages = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public List<String> getParticipantIds() {
        return participantIds;
    }

    public List<Message> getMessages() {
        return messages;
    }
}
