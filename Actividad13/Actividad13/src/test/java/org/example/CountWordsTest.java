package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class CountWordsTest {
    @Test
    void twoWordsEndingWithS(){
        int words = new CountWords().count("dogs cats");
        assertThat(words).isEqualTo(2);
    }
    @Test
    void noWordsAtAll(){
        int words = new CountWords().count("dog cat");
        assertThat(words).isEqualTo(0);
    }
    @Test
    void wordsThatEndInR() { // 1
        int words = new CountWords().count("car bar");
        assertThat(words).isEqualTo(2);
    }
}