package com.hu.lingo.trainer.importer.core.domain;

import org.springframework.stereotype.Service;

@Service
public class LingoWordFilter implements WordFilter {

    /** only accept lower case letters with words where length >= 5 and <= 7 */
    @Override
    public boolean verify(String word) {
        return word.matches("^[a-z]{5,7}$");
    }

}
