package com.hu.lingo.trainer.importer.core.ports;

import java.util.stream.Stream;

public interface WordReader {
    Stream<String> readWords();
}
