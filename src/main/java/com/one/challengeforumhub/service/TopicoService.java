package com.one.challengeforumhub.service;

import com.one.challengeforumhub.domain.Topico;
import com.one.challengeforumhub.dto.topico.TopicoRequestDto;
import com.one.challengeforumhub.exception.TopicoDuplicadoException;
import com.one.challengeforumhub.exception.EntidadeNaoEncontradaException;
import com.one.challengeforumhub.repository.TopicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    private final TopicoRepository topicoRepository;

    public TopicoService(TopicoRepository topicoRepository) {
        this.topicoRepository = topicoRepository;
    }

    public Topico criarTopico(final TopicoRequestDto dto) {
        final var topico = new Topico(dto);
        final var existeDuplicado = topicoRepository.existsTopicoByTituloIgnoreCaseAndMensagemIgnoreCase(topico.getTitulo(), topico.getMensagem());

        if (existeDuplicado) {
            throw new TopicoDuplicadoException("Já existe um tópico com esse título e mensagem.");
        }

        return topicoRepository.save(topico);
    }

    public Page<Topico> listarTodos(final Pageable pageable) {
        return topicoRepository.findAll(pageable);
    }

    public Topico buscarPorId(final Long id) {
        return topicoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Tópico com ID " + id + " não encontrado."));
    }

    @Transactional
    public Topico alterarTopico(final Long id, final TopicoRequestDto dto) {
        final var topico = this.buscarPorId(id);
        topico.alterar(dto);

        return topico;
    }

    public void deletarPorId(final Long id) {
        verificaSeExistePorId(id);
        topicoRepository.deleteById(id);
    }

    private void verificaSeExistePorId(final Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Tópico com ID " + id + " não encontrado.");
        }
    }
}
