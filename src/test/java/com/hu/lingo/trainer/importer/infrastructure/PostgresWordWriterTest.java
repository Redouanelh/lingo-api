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

    private static WordService mockFileService;

    private List<String> words = Arrays.asList("afweten", "basic", "basket", "eropaf", "eropin");

    @BeforeAll
    static void beforeAll() {
        WordRepository spyWordRepository = spy(WordRepository.class);
        mockFileService = mock(WordService.class, withSettings().useConstructor(spyWordRepository));
    }

    @Test
    void writing_all_words_to_db_once() {
        PostgresWordWriter mockWriter = mock(PostgresWordWriter.class, withSettings().useConstructor(mockFileService));

        mockWriter.writeWords(this.words);

        verify(mockWriter, atMost(1))
                .writeWords(this.words);
        
    }

    @Test
    void clear_all_words_from_db_once() {
        PostgresWordWriter mockWriter = mock(PostgresWordWriter.class, withSettings().useConstructor(mockFileService));

        mockWriter.clearAll();

        verify(mockWriter, atMost(1))
                .clearAll();
    }
}
