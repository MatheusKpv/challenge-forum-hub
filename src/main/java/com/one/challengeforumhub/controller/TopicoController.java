package com.one.challengeforumhub.controller;

import com.one.challengeforumhub.domain.Topico;
import com.one.challengeforumhub.dto.CriarTopicoRequestDto;
import com.one.challengeforumhub.dto.TopicoDetalhadoDto;
import com.one.challengeforumhub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    private final TopicoService topicoService;

    public TopicoController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }

    @GetMapping
    public ResponseEntity<Page<Topico>> listarTopicos(@PageableDefault(size = 5, sort = "dataCriacao", direction = Sort.Direction.ASC) Pageable pageable) {
        final var page = topicoService.listarTodos(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<TopicoDetalhadoDto> criarTopico(@RequestBody @Valid CriarTopicoRequestDto dto, UriComponentsBuilder uriBuilder) {
        final var topico = topicoService.criarTopico(dto);

        final var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        final var response = new TopicoDetalhadoDto(topico);

        return ResponseEntity.created(uri).body(response);
    }
}
