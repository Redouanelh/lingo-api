package com.hu.lingo.trainer.importer.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class NonExistentException extends RuntimeException {
    public NonExistentException(String message) {
        super(message);
    }
}
