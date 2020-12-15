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

    /** Import words from source file using a filter, shuffle the list of words,
     *  give the amount of words you would like, write the filtered words to a text file */
    public void importWords(int amount) {
        this.filteredWords = wordReader
                .readWords()
                .filter(wordFilter::verify)
                .collect(Collectors.toList());

        Collections.shuffle(this.filteredWords);
        this.filteredWords = this.filteredWords.subList(0, amount);

        wordWriter.writeWords(this.filteredWords);
    }

    /** First remove al words from the database, then save all the words from
     * the filtered words text file to the database */
    public void importWordsToDatabase() {
        dbWriter.clearAll();
        dbWriter.writeWords(this.filteredWords);
    }
}
