package com.one.challengeforumhub.dto.topico;

import jakarta.validation.constraints.NotEmpty;

public record TopicoRequestDto(
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
