package com.one.challengeforumhub.dto.authentication;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDto(
        @NotBlank
        String login,

        @NotBlank
        String senha
) {
}
