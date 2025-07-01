package com.one.challengeforumhub.controller;

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
    public ResponseEntity<Page<TopicoDetalhadoDto>> listarTopicos(@PageableDefault(size = 5, sort = "dataCriacao", direction = Sort.Direction.ASC) Pageable pageable) {
        final var page = topicoService.listarTodos(pageable);
        final var pageDto = page.map(TopicoDetalhadoDto::new);
        return ResponseEntity.ok(pageDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<TopicoDetalhadoDto> listarTopico(@PathVariable Long id) {
        final var topico = topicoService.buscarPorId(id);
        return ResponseEntity.ok(new TopicoDetalhadoDto(topico));
    }

    @PostMapping
    public ResponseEntity<TopicoDetalhadoDto> criarTopico(@RequestBody @Valid CriarTopicoRequestDto dto, UriComponentsBuilder uriBuilder) {
        final var topico = topicoService.criarTopico(dto);

        final var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        final var response = new TopicoDetalhadoDto(topico);

        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<TopicoDetalhadoDto> alterarTopico(@PathVariable Long id, @RequestBody @Valid CriarTopicoRequestDto dto) {
        final var topicoAtualizado = topicoService.alterarTopico(id, dto);
        return ResponseEntity.ok(new TopicoDetalhadoDto(topicoAtualizado));
    }
}
