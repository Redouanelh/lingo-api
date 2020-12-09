package com.hu.lingo.trainer.importer.core.ports;

import java.util.List;

public interface WordWriter {
    void writeWords(List<String> words);
    void clearAll();
}
