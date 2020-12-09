package com.hu.lingo.trainer.importer.core.application;

import com.hu.lingo.trainer.importer.core.domain.WordFilter;
import com.hu.lingo.trainer.importer.core.ports.WordReader;
import com.hu.lingo.trainer.importer.core.ports.WordWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WordImporter {
    private final WordReader wordReader;
    private final WordFilter wordFilter;
    private final WordWriter wordWriter;
    private final WordWriter dbWriter;

    private List<String> filteredWords;

    public WordImporter(WordReader reader, WordFilter filter, @Qualifier("txtFileWordWriter") WordWriter writer,  @Qualifier("postgresWordWriter") WordWriter dbWriter) {
        this.wordReader = reader;
        this.wordFilter = filter;
        this.wordWriter = writer;
        this.dbWriter = dbWriter;
    }

    public void importWords(int amount) {
        this.filteredWords = wordReader
                .readWords()
                .filter(wordFilter::verify)
                .collect(Collectors.toList());

        Collections.shuffle(this.filteredWords);
        this.filteredWords = this.filteredWords.subList(0, amount);

        wordWriter.writeWords(this.filteredWords);
    }

    public void importWordsToDatabase() {
        dbWriter.clearAll();
        dbWriter.writeWords(this.filteredWords);
    }
}
