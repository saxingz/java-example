package org.saxing.interpreter;

/**
 * Expression
 *
 * @author saxing 2019/2/13 21:30
 */
public abstract class Expression {

    public abstract int interpret();

    @Override
    public abstract String toString();

}
