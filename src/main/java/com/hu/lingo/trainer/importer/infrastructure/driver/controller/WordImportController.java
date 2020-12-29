package com.hu.lingo.trainer.importer.infrastructure.driver.controller;

import com.hu.lingo.trainer.importer.core.application.WordService;
import com.hu.lingo.trainer.importer.core.domain.entity.ValidWord;
import com.hu.lingo.trainer.importer.error.CapitalException;
import com.hu.lingo.trainer.importer.error.NonExistentException;
import com.hu.lingo.trainer.importer.error.PunctuationException;
import com.hu.lingo.trainer.importer.error.WrongLengthException;
import org.springframework.stereotype.Service;

@Service
public class WordImportController {
    private final WordService wordService;

    public WordImportController(WordService wordService) {
        this.wordService = wordService;
    }

    public String randomWord(int length) {
        return this.wordService.randomWord(length).getWord();
    }

    public Boolean guessValidator(String word, String guess) {
        ValidWord validWord = this.wordService.guessValidator(word, guess);

        if (validWord.equals(ValidWord.NONEXISTENT)) throw new NonExistentException("Word does not exist!");
        if (validWord.equals(ValidWord.WRONG_LENGTH)) throw new WrongLengthException(String.format("Word should consist out of %s letters!", word.length()));
        if (validWord.equals(ValidWord.CAPITAL)) throw new CapitalException("Word cannot contain capital characters!");
        if (validWord.equals(ValidWord.PUNCTUATION)) throw new PunctuationException("Word cannot contain special characters!");

        return true; // ValidWord.VALID
    }
}
