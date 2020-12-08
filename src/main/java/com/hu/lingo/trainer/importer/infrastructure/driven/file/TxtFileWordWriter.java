package com.hu.lingo.trainer.importer.infrastructure.driven.file;

import com.hu.lingo.trainer.importer.core.ports.WordWriter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TxtFileWordWriter implements WordWriter {
    @Override
    public void writeWords(List<String> words) {

    }
}
