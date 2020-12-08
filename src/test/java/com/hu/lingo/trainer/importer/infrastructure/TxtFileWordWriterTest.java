package com.hu.lingo.trainer.importer.infrastructure;

import com.hu.lingo.trainer.application.error.WritingToFileException;
import com.hu.lingo.trainer.importer.infrastructure.driven.file.TxtFileWordWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class TxtFileWordWriterTest {
    private Path target;
    private List<String> words = Arrays.asList("afweten", "basic", "basket", "eropaf", "eropin");

    @BeforeEach
    void beforeEach() {
        this.target = Path.of("", "src/test/resources/target.txt");
    }

    @Test
    void writing_words_to_file_once() {
        TxtFileWordWriter mockWriter = mock(TxtFileWordWriter.class);

        mockWriter.writeWords(this.words);
        verify(mockWriter, atMostOnce()).writeWords(this.words);
    }

    @Test
    void writing_all_words_to_target() throws IOException {
        TxtFileWordWriter writer = new TxtFileWordWriter(this.target);
        writer.writeWords(this.words);

        List<String> target_words = Files.lines(this.target).collect(Collectors.toList());

        assertEquals(this.words, target_words);
    }

//    @Test
//    void throws_writing_to_file_exception() {
//        TxtFileWordWriter writer = new TxtFileWordWriter(this.target);
//        List<String> empty = new ArrayList<>();
//
//        assertThrows(WritingToFileException.class, () -> {
//            writer.writeWords(empty);
//        });
//    }

}
