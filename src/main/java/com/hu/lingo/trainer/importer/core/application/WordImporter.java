package com.hu.lingo.trainer.importer.core.application;

import com.hu.lingo.trainer.importer.core.domain.WordFilter;
import com.hu.lingo.trainer.importer.core.ports.WordReader;
import com.hu.lingo.trainer.importer.core.ports.WordWriter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WordImporter {
    private final WordReader wordReader;
    private final WordFilter wordFilter;
    private final WordWriter wordWriter;

    public WordImporter(WordReader reader, WordFilter filter, WordWriter writer) {
        this.wordReader = reader;
        this.wordFilter = filter;
        this.wordWriter = writer;
    }

    public void importWords() {
        List<String> filteredWords = wordReader
                .readWords()
                .filter(wordFilter::verify)
                .collect(Collectors.toList());

        wordWriter.writeWords(filteredWords);
    }
}
