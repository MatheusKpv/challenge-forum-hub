package com.one.challengeforumhub.dto;

import com.one.challengeforumhub.domain.Topico;

import java.time.LocalDateTime;

public record TopicoDetalhadoDto(
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        String estado,
        String autor,
        String curso
) {
    public TopicoDetalhadoDto(final Topico topico) {
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
