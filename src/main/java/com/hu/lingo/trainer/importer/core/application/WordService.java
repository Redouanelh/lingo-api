package com.hu.lingo.trainer.importer.core.application;

import com.hu.lingo.trainer.importer.core.domain.entity.Word;
import com.hu.lingo.trainer.importer.core.domain.entity.WordRepository;
import org.springframework.stereotype.Service;

@Service
public class WordService extends BaseService<Word> {
    private WordRepository wordRepository;

    public WordService(WordRepository wordRepository) {
        super(wordRepository);
        this.wordRepository = wordRepository;
    }

    public Word save(String word) {
        return this.wordRepository.save(new Word(word));
    }

    public void clearAllWords() {
        this.wordRepository.deleteAll();
    }
}
