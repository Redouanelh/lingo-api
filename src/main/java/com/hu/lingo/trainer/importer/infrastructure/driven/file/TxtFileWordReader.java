package com.hu.lingo.trainer.importer.infrastructure.driven.file;

import com.hu.lingo.trainer.importer.error.InvalidFileException;
import com.hu.lingo.trainer.importer.core.ports.WordReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@Service
public class TxtFileWordReader implements WordReader {

    private final Path source;

    public TxtFileWordReader(@Value("${lingo.source}") Path source) {
        this.source = source;
    }

    /** Read all words from source file and returns a stream of strings (words) */
    @Override
    public Stream<String> readWords() {
        try {
            return Files.lines(this.source);
        } catch (IOException e) {
            throw new InvalidFileException("Invalid file path for reading...");
        }
    }
}
