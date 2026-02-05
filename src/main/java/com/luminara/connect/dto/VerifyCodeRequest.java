package com.luminara.connect.dto;

import jakarta.validation.constraints.NotBlank;

public record VerifyCodeRequest(
        @NotBlank String phoneNumber,
        @NotBlank String code
) {
}
