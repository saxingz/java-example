package org.saxing.interpreter;

/**
 * MinusExpression
 *
 * @author saxing 2019/2/13 21:31
 */
public class MinusExpression extends Expression {

    private Expression leftExpression;
    private Expression rightExpression;

    public MinusExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public int interpret() {
        return leftExpression.interpret() - rightExpression.interpret();
    }

    @Override
    public String toString() {
        return "-";
    }
}
