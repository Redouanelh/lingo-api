package com.hu.lingo.trainer.importer.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class PunctuationException extends RuntimeException {
    public PunctuationException(String message) {
        super(message);
    }
}
