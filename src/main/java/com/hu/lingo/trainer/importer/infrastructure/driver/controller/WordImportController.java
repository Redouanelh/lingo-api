package com.hu.lingo.trainer.importer.infrastructure.driver.controller;

import com.hu.lingo.trainer.importer.core.application.WordService;
import org.springframework.stereotype.Service;

@Service
public class WordImportController {
    private final WordService wordService;

    public WordImportController(WordService wordService) {
        this.wordService = wordService;
    }

    public String randomWord(int length) {
        return this.wordService.randomWord(length).getWord();
    }
}
