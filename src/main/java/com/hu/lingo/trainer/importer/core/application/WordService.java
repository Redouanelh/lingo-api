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

    /** Save a word */
    public Word save(String word) {
        return this.wordRepository.save(new Word(word));
    }

    /** Remove all words from the database */
    public void clearAllWords() {
        this.wordRepository.deleteAll();
    }
}
