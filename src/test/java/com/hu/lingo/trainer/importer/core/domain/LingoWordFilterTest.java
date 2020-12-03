package com.hu.lingo.trainer.importer.core.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LingoWordFilterTest {
    private LingoWordFilter filter;

    @BeforeEach
    void beforeEach() {
        this.filter = new LingoWordFilter();
    }

//    @ParameterizedTest
//    @MethodSource("provideWordsOfVaryingLength")
//    void accepts_words_of_5_6_7_letters_only(String word, boolean shouldAccept) {
//        boolean accepts = this.filter.verify(word);
//        assertEquals(shouldAccept, accepts);
//    }
//
//
//    @ParameterizedTest
//    @MethodSource("provideWordsOfVaryingCase")
//    void accepts_words_of_lowercase_only(String word, boolean shouldAccept) {
//        boolean accepts = this.filter.verify(word);
//        assertEquals(shouldAccept, accepts);
//    }
//
//    @ParameterizedTest
//    @MethodSource("provideWordsOfVaryingSymbol")
//    void rejects_words_with_symbols(String word, boolean shouldAccept) {
//        boolean accepts = this.filter.verify(word);
//        assertEquals(shouldAccept, accepts);
//    }

    static Stream<Arguments> provideWordsOfVaryingLength() {
        return Stream.of(
                Arguments.of("steen", true), // 5 letter
                Arguments.of("banaan", true), // 6 letter
                Arguments.of("bananen", true), // 7 letter
                Arguments.of("bron", false),
                Arguments.of("aanhaken", false)
        );
    }

    static Stream<Arguments> provideWordsOfVaryingCase() {
        return Stream.of(
                Arguments.of("steen", true),
                Arguments.of("Steen", false),
                Arguments.of("SteeN", false),
                Arguments.of("steeN", false),
                Arguments.of("stEen", false),
                Arguments.of("STEEN", false)
        );
    }

    static Stream<Arguments> provideWordsOfVaryingSymbol() {
        return Stream.of(
                Arguments.of("steen", true),
                Arguments.of("st:,n", false),
                Arguments.of("stéén", false),
                Arguments.of("ste n", false),
                Arguments.of("p̣̝̔̂͝i̻͚ͅz̰̟̖̩̩̘z̖̭̞͉͍̕a͖͛̓͘", false),
                Arguments.of("st@#$", false),
                Arguments.of("st-=+", false),
                Arguments.of("st33n", false)
        );
    }
}
