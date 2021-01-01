package com.hu.lingo.trainer.importer.core.application;

import com.hu.lingo.trainer.importer.core.domain.entity.ValidWord;
import com.hu.lingo.trainer.importer.core.domain.entity.Word;
import com.hu.lingo.trainer.importer.core.domain.entity.WordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.IntPredicate;

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

    /* Returns random word with given length */
    @Transactional
    public Word randomWord(int length) {
        List<Word> words = this.wordRepository.getWordsByLength(length);
        Random rand = new Random();

        return words.get(rand.nextInt(words.size()));
    }

    @Transactional
    public ValidWord guessValidator(String word, String guess) {
        Word actualWord = new Word(word, word.length());
        Optional<Word> guessedWord = this.wordRepository.findWordByWord(guess.toLowerCase());

        if (guessedWord.isEmpty()) return ValidWord.NONEXISTENT;
        if (guess.length() > actualWord.getLength() || guess.length() < actualWord.getLength()) return ValidWord.WRONG_LENGTH;
        if (contains(guess, i -> Character.isLetter(i) && Character.isUpperCase(i))) return ValidWord.CAPITAL;
        if (guess.matches("[^a-zA-Z0-9]+")) return ValidWord.PUNCTUATION;

        return ValidWord.VALID;
    }

    /** Remove all words from the database */
    @Transactional
    public void clearAllWords() {
        this.wordRepository.truncateTable();
    }

    /** Using this method for validating a guess */
    private boolean contains(String value, IntPredicate predicate) {
        return value.chars().anyMatch(predicate);
    }
}
