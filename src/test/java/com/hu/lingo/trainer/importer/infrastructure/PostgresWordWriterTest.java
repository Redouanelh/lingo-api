package com.hu.lingo.trainer.importer.infrastructure;

import com.hu.lingo.trainer.importer.core.application.WordService;
import com.hu.lingo.trainer.importer.core.domain.entity.Word;
import com.hu.lingo.trainer.importer.core.domain.entity.WordRepository;
import com.hu.lingo.trainer.importer.infrastructure.driven.db.PostgresWordWriter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class PostgresWordWriterTest {

    private static WordRepository spyWordRepository;
    private static WordService mockWordService;
    private static PostgresWordWriter wordWriter;

    private List<String> words = Arrays.asList("basic", "basic", "basic", "basic", "basic");

    @BeforeAll
    static void beforeAll() {
        spyWordRepository = spy(WordRepository.class);
        mockWordService = mock(WordService.class, withSettings().useConstructor(spyWordRepository));

        wordWriter = new PostgresWordWriter(mockWordService);
    }

    @Test
    void removing_all_words_from_db_once() {
        String word = words.get(0);

        when(spyWordRepository.save(new Word(word)))
                .thenReturn(new Word(word));

        wordWriter.clearAll();

        verify(mockWordService, times(1))
                .clearAllWords();
    }

}
