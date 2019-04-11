package org.saxing.layers;

/**
 * Custom exception used in cake baking
 *
 * @author saxing 2019/4/11 23:04
 */
public class CakeBakingException extends Exception {

    private static final long serialVersionUID = 1L;

    public CakeBakingException() {}

    public CakeBakingException(String message) {
        super(message);
    }

}
