package org.saxing.interpreter;

/**
 * NumberExpression
 *
 * @author saxing 2019/2/13 21:34
 */
public class NumberExpression extends Expression {

    private int number;

    public NumberExpression(int number) {
        this.number = number;
    }

    public NumberExpression(String s) {
        this.number = Integer.parseInt(s);
    }

    @Override
    public int interpret() {
        return number;
    }

    @Override
    public String toString() {
        return "number";
    }
}
