package com.one.challengeforumhub.dto;

import jakarta.validation.constraints.NotEmpty;

public record CriarTopicoRequestDto(
        @NotEmpty
        String titulo,

        @NotEmpty
        String mensagem,

        @NotEmpty
        String autor,

        @NotEmpty
        String curso
) {
}
