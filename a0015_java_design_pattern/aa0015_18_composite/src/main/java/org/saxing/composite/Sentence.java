package org.saxing.composite;

import java.util.List;

/**
 * Sentence
 *
 * @author saxing  2018/11/24 12:59
 */
public class Sentence extends LetterComposite {

    public Sentence(List<Word> words) {
        for (Word word : words){
            this.add(word);
        }
    }

    @Override
    protected void printThisAfter() {
        System.out.println(".");
    }
}
