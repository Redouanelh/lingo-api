package com.hu.lingo.trainer.importer.core.application;

import com.hu.lingo.trainer.importer.core.domain.entity.Word;
import com.hu.lingo.trainer.importer.core.domain.entity.WordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WordService extends BaseService<Word> {
    private WordRepository wordRepository;

    public WordService(WordRepository wordRepository) {
        super(wordRepository);
        this.wordRepository = wordRepository;
    }

    /** Save a word */
    public Word save(String word) {
        return this.wordRepository.save(new Word(word, word.length()));
    }

    /** Remove all words from the database */
    @Transactional
    public void clearAllWords() {
//        this.wordRepository.deleteAll();
        this.wordRepository.truncateTable();
    }
}
