package com.one.challengeforumhub.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TopicoInexistenteException extends RuntimeException {
    public TopicoInexistenteException(final String message) {
        super(message);
    }
}
