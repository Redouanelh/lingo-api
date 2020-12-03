package com.hu.lingo.trainer.importer.infrastructure.driven.file;

import com.hu.lingo.trainer.importer.core.ports.WordReader;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class TxtFileWordReader implements WordReader {
    @Override
    public Stream<String> readWords() {
        return null;
    }
}
