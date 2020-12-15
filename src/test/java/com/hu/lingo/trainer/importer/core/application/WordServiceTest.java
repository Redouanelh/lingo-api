package com.hu.lingo.trainer.importer.core.application;

import com.hu.lingo.trainer.importer.core.domain.entity.Word;
import com.hu.lingo.trainer.importer.core.domain.entity.WordRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class WordServiceTest {

    private static WordRepository spyWordRepository;
    private static WordService wordService;

    @BeforeAll
    static void beforeAll() {
        spyWordRepository = spy(WordRepository.class);

        wordService = new WordService(spyWordRepository);
    }

    @Test
    void saving_word_to_database_once() {
        String word = "pizza";

        when(wordService.save(word))
                .thenReturn(new Word(word));

        verify(spyWordRepository, atMost(1))
                .save(new Word(word));
    }

    @Test
    void clearing_all_words_from_database_once() {
        wordService.clearAllWords();

        verify(spyWordRepository, atMost(1))
                .deleteAll();
    }
}
