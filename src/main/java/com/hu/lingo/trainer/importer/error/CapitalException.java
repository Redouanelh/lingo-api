package com.hu.lingo.trainer.importer.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class CapitalException extends RuntimeException {
    public CapitalException(String message) {
        super(message);
    }
}
