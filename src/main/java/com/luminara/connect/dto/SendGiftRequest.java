package com.luminara.connect.dto;

import jakarta.validation.constraints.NotBlank;

public record SendGiftRequest(
        @NotBlank String fromUserId,
        @NotBlank String toUserId,
        @NotBlank String giftCode,
        String message
) {
}
