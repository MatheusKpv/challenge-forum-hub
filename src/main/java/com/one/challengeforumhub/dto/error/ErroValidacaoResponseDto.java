package com.one.challengeforumhub.dto.error;

import java.util.List;

public record ErroValidacaoResponseDto(
        String mensagem,
        List<CampoErroDto> erros
) {
}
