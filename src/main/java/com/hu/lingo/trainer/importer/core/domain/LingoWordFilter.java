package com.hu.lingo.trainer.importer.core.domain;

public class LingoWordFilter implements WordFilter {

    @Override
    public boolean verify(String word) {
        return word.matches("^[a-z]{5,7}$");
    }

}
