package com.luminara.connect.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record TopUpRequest(
        @NotBlank String userId,
        @DecimalMin(value = "0.01") BigDecimal amount
) {
}
