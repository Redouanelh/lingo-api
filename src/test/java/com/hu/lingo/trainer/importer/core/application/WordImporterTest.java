package com.hu.lingo.trainer.importer.core.application;

import com.hu.lingo.trainer.importer.core.domain.WordFilter;
import com.hu.lingo.trainer.importer.core.ports.WordReader;
import com.hu.lingo.trainer.importer.core.ports.WordWriter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

class WordImporterTest {

    private static WordReader mockReader;
    private static WordFilter mockFilter;
    private static WordWriter spyWriter;
    private static WordWriter dbSpyWriter;

    private static WordImporter wordImporter;

    @BeforeAll
    static void beforeAll() {
        mockReader = mock(WordReader.class);
        mockFilter = mock(WordFilter.class);
        spyWriter = spy(WordWriter.class);
        dbSpyWriter = spy(WordWriter.class);

        wordImporter = new WordImporter(
                mockReader,
                mockFilter,
                spyWriter,
                dbSpyWriter
        );
    }

    @Test
    void import_words_from_a_reader_to_a_writer_using_a_filter() {
        List<String> wordList = List.of(
                "papa",
                "kaasje",
                "bieren",
                "stevig",
                "kapot"
        );

        when(mockReader.readWords())
                .thenReturn(wordList.stream());

        when(mockFilter.verify(anyString()))
                .thenReturn(true);

        wordImporter.importWords();

        verify(spyWriter, atMost(1))
                .writeWords(wordList);
    }

    @Test
    void imports_filtered_words_to_database_once() {
        List<String> wordList = List.of(
                "papa",
                "kaasje",
                "bieren",
                "stevig",
                "kapot"
        );

        wordImporter.importWordsToDatabase();

        verify(dbSpyWriter, atMost(1))
                .clearAll();

        verify(dbSpyWriter, atMost(1))
                .writeWords(wordList);
    }

}
