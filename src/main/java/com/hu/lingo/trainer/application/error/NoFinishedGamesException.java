package com.hu.lingo.trainer.application.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoFinishedGamesException extends RuntimeException {
    public NoFinishedGamesException(String message) {
        super(message);
    }
}
