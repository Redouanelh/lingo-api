package com.hu.lingo.trainer.application.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class GameAlreadyExistsException extends RuntimeException {
    public GameAlreadyExistsException(String message) {
        super(message);
    }
}
