package org.saxing.interpreter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Stack;

/**
 * main
 *
 * @author saxing 2019/2/13 21:35
 */
public class Aa001555InterpreterApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(Aa001555InterpreterApplication.class);

    public static void main(String[] args) {
        String tokenString = "4 3 2 - 1 + *";
        Stack<Expression> stack = new Stack<>();

        String[] tokenList = tokenString.split(" ");
        for (String s : tokenList){
            if (isOperator(s)){
                Expression rightExpression = stack.pop();
                Expression leftExpression = stack.pop();
                LOGGER.info("popped from stack left: {} right: {}",
                        leftExpression.interpret(), rightExpression.interpret());
                Expression operator = getOperatorInstance(s, leftExpression, rightExpression);
                LOGGER.info("operator: {}", operator);
                int result = operator.interpret();
                NumberExpression resultExpression = new NumberExpression(result);
                stack.push(resultExpression);
                LOGGER.info("push result to stack: {}", resultExpression.interpret());
            }else{
                Expression i = new NumberExpression(s);
                stack.push(i);
                LOGGER.info("push to stack: {}", i.interpret());
            }
        }
        LOGGER.info("result: {}", stack.pop().interpret());
    }

    public static boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*");
    }

    /**
     * Get expression for string
     */
    public static Expression getOperatorInstance(String s, Expression left, Expression right) {
        switch (s) {
            case "+":
                return new PlusExpression(left, right);
            case "-":
                return new MinusExpression(left, right);
            case "*":
                return new MultiplyExpression(left, right);
            default:
                return new MultiplyExpression(left, right);
        }
    }

}

