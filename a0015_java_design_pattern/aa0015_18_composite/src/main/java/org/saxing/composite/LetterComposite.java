package org.saxing.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * LetterComposite
 *
 * @author saxing  2018/11/24 12:54
 */
public abstract class LetterComposite {

    private List<LetterComposite> children = new ArrayList<>();

    public void add(LetterComposite letter){
        children.add(letter);
    }

    public int count(){
        return children.size();
    }

    protected void printThisBefore(){}

    protected void printThisAfter(){}

    public void print(){
        printThisBefore();
        for (LetterComposite letter : children){
            letter.print();
        }
        printThisAfter();
    }

}
