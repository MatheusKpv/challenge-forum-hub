package com.one.challengeforumhub.dto.topico;

import com.one.challengeforumhub.domain.Topico;

import java.time.LocalDateTime;

public record TopicoResponseDto(
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        String estado,
        String autor,
        String curso
) {
    public TopicoResponseDto(final Topico topico) {
        this(
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getEstado().name(),
                topico.getAutor(),
                topico.getCurso()
        );
    }
}
