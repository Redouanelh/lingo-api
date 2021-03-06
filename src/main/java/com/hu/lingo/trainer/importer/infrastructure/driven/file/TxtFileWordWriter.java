package com.hu.lingo.trainer.importer.infrastructure.driven.file;

import com.hu.lingo.trainer.importer.error.ClearFileException;
import com.hu.lingo.trainer.importer.error.WritingToFileException;
import com.hu.lingo.trainer.importer.core.ports.WordWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class TxtFileWordWriter implements WordWriter {
    private final Path target;

    public TxtFileWordWriter(@Value("${lingo.target}") Path target) {
        this.target = target;
    }

    /** Writing each word from the list to a target text file. This list only contains valid words */
    @Override
    public void writeWords(List<String> words) {
        try {
            Files.writeString(this.target, String.join("\n", words));
        } catch (Exception e) {
            throw new WritingToFileException("Failed writing strings to file...");
        }
    }

    /** Clears text file */
    @Override
    public void clearAll() {
        try {
            Files.writeString(this.target, String.join("\n", ""));
        } catch (Exception e) {
            throw new ClearFileException("Failed clearing file...");
        }
    }
}
