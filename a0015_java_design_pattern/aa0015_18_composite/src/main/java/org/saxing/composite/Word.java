package org.saxing.composite;

import java.util.List;

/**
 * Word
 *
 * @author saxing  2018/11/24 13:00
 */
public class Word extends LetterComposite{

    public Word(List<Letter> letters) {
        for (Letter l : letters){
            this.add(l);
        }
    }

    @Override
    protected void printThisBefore() {
        System.out.print(" ");
    }
}
