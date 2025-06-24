package com.one.challengeforumhub.repository;

import com.one.challengeforumhub.domain.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsTopicoByTituloIgnoreCaseAndMensagemIgnoreCase(String titulo, String mensagem);
}
