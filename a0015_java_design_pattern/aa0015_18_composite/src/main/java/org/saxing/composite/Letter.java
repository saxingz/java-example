package org.saxing.composite;

/**
 * Letter
 *
 * @author saxing  2018/11/24 12:58
 */
public class Letter extends LetterComposite {

    private char c;

    public Letter(char c) {
        this.c = c;
    }

    @Override
    protected void printThisBefore() {
        System.out.print(c);
    }
}
