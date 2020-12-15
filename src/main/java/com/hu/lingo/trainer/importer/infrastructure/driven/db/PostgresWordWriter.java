package com.hu.lingo.trainer.importer.infrastructure.driven.db;

import com.hu.lingo.trainer.importer.core.application.WordService;
import com.hu.lingo.trainer.importer.core.ports.WordWriter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostgresWordWriter implements WordWriter {
    private final WordService wordService;

    public PostgresWordWriter(WordService wordService) {
        this.wordService = wordService;
    }

    /** Save each word from the list in the database */
    @Override
    public void writeWords(List<String> words) {
        for (String word: words) {
            this.wordService.save(word);
        }
    }

    /** Remove all words from the database */
    @Override
    public void clearAll() {
        this.wordService.clearAllWords();
    }
}
