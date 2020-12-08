package com.hu.lingo.trainer.importer.infrastructure;

import com.hu.lingo.trainer.application.error.InvalidFileException;
import com.hu.lingo.trainer.importer.infrastructure.driven.file.TxtFileWordReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TxtFileWordReaderTest {
    private Path source;

    @BeforeEach
    void beforeEach() {
        this.source = Path.of("", "src/test/resources/source.txt");
    }

    @Test
    void only_contains_stream_of_strings() {
        TxtFileWordReader reader = new TxtFileWordReader(this.source);

        Stream<String> words = reader.readWords();
        assertThat(words).hasOnlyElementsOfType(String.class);
    }

    @Test
    void throws_invalid_file_exception() {
        this.source = Path.of("", "src/test/resources/does_not_exist.txt");

        TxtFileWordReader reader = new TxtFileWordReader(this.source);
        assertThrows(InvalidFileException.class, reader::readWords);
    }

}
