package com.hu.lingo.trainer.importer.core.application;

import com.hu.lingo.trainer.importer.core.domain.WordFilter;
import com.hu.lingo.trainer.importer.core.ports.WordReader;
import com.hu.lingo.trainer.importer.core.ports.WordWriter;
import org.springframework.stereotype.Service;

@Service
public class WordImporter {
    private final WordReader reader;
    private final WordFilter filter;
    private final WordWriter writer;

    public WordImporter(WordReader reader, WordFilter filter, WordWriter writer) {
        this.reader = reader;
        this.filter = filter;
        this.writer = writer;
    }
}
