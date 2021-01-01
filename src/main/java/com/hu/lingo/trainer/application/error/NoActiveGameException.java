package com.hu.lingo.trainer.application.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoActiveGameException extends RuntimeException {
    public NoActiveGameException(String message) { super(message); }
}
