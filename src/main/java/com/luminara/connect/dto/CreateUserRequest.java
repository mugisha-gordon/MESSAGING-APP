package com.luminara.connect.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(
        @NotBlank String displayName,
        @NotBlank String phoneNumber
) {
}
