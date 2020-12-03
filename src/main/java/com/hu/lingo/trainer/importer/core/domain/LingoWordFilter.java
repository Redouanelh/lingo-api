package com.hu.lingo.trainer.importer.core.domain;

public class LingoWordFilter implements WordFilter {

    @Override
    public boolean verify(String word) {
        // only accept lower case letters with words where length >= 5 and <= 7
        return word.matches("^[a-z]{5,7}$");
    }

}
