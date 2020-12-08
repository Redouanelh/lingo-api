package com.hu.lingo.trainer.importer.core.application;

import com.hu.lingo.trainer.importer.core.domain.WordFilter;
import com.hu.lingo.trainer.importer.core.ports.WordReader;
import com.hu.lingo.trainer.importer.core.ports.WordWriter;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

class WordImporterTest {

    @Test
    void import_words_from_a_reader_to_a_writer_using_a_filter() {
        List<String> wordList = List.of(
                "papa",
                "kaasje",
                "bieren",
                "stevig",
                "kapot"
        );

        WordReader mockReader = mock(WordReader.class);
        when(mockReader.readWords())
                .thenReturn(wordList.stream());

        WordFilter mockFilter = mock(WordFilter.class);
        when(mockFilter.verify(anyString()))
                .thenReturn(true);

        WordWriter spyWriter = spy(WordWriter.class);

        WordImporter wordImporter = new WordImporter(
                            mockReader,
                            mockFilter,
                            spyWriter
        );

        wordImporter.importWords();

        verify(spyWriter, times(1))
                .writeWords(wordList);
    }
}
