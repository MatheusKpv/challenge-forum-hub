package com.one.challengeforumhub.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class TopicoDuplicadoException extends RuntimeException {
    public TopicoDuplicadoException(final String message) {
        super(message);
    }
}
