package com.hu.lingo.trainer.application.error;

public class WritingToFileException extends RuntimeException {
    public WritingToFileException(String message) {
        super(message);
    }
}
