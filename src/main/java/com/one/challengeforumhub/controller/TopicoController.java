package com.one.challengeforumhub.controller;

import com.one.challengeforumhub.dto.topico.TopicoRequestDto;
import com.one.challengeforumhub.dto.topico.TopicoResponseDetalhadoDto;
import com.one.challengeforumhub.dto.topico.TopicoResponseDto;
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
    public ResponseEntity<Page<TopicoResponseDetalhadoDto>> listarTopicos(@PageableDefault(size = 5, sort = "dataCriacao", direction = Sort.Direction.ASC) Pageable pageable) {
        final var page = topicoService.listarTodos(pageable);
        final var pageDto = page.map(TopicoResponseDetalhadoDto::new);
        return ResponseEntity.ok(pageDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<TopicoResponseDto> listarTopico(@PathVariable Long id) {
        final var topico = topicoService.buscarPorId(id);
        return ResponseEntity.ok(new TopicoResponseDto(topico));
    }

    @PostMapping
    public ResponseEntity<TopicoResponseDto> criarTopico(@RequestBody @Valid TopicoRequestDto dto, UriComponentsBuilder uriBuilder) {
        final var topico = topicoService.criarTopico(dto);

        final var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        final var response = new TopicoResponseDto(topico);

        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<TopicoResponseDto> alterarTopico(@PathVariable Long id, @RequestBody @Valid TopicoRequestDto dto) {
        final var topicoAtualizado = topicoService.alterarTopico(id, dto);
        return ResponseEntity.ok(new TopicoResponseDto(topicoAtualizado));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> alterarTopico(@PathVariable Long id) {
        topicoService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
