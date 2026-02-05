package com.luminara.connect.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthCodeRequest(
        @NotBlank String phoneNumber,
        String displayName
) {
}
