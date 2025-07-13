package com.one.challengeforumhub.exception;

import com.one.challengeforumhub.dto.error.CampoErroDto;
import com.one.challengeforumhub.dto.error.ErroValidacaoResponseDto;
import com.one.challengeforumhub.dto.error.ErrorMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TopicoDuplicadoException.class)
    public ResponseEntity<ErrorMessageDto> handleTopicoNaoEncontrado(TopicoDuplicadoException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessageDto(ex.getMessage()));
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<ErrorMessageDto> handleTopicoNaoEncontrado(EntidadeNaoEncontradaException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessageDto(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroValidacaoResponseDto> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        final var erros = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(erro -> new CampoErroDto(erro.getField(), erro.getDefaultMessage()))
                .toList();

        return ResponseEntity.badRequest()
                .body(new ErroValidacaoResponseDto("Dados inválidos", erros));
    }
}
