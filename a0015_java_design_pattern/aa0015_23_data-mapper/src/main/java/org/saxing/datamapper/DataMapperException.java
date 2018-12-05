package org.saxing.datamapper;

/**
 * Using Runtime Exception for avoiding dependancy on implementation exceptions. 
 * 
 * @author saxing 2018/12/5 20:58
 */
public class DataMapperException extends RuntimeException {
    private static final long serialVersionUID = -303684044493198019L;

    public DataMapperException(final String message) {
        super(message);
    }

}
