package com.luminara.connect.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record CreateChatRequest(@NotEmpty List<String> participantIds) {
}
