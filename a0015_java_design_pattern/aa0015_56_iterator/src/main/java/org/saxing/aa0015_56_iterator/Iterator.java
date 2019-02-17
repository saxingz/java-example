package org.saxing.aa0015_56_iterator;

/**
 * Iterator interface to be implemented by iterators over various data structures
 *
 * @author saxing 2019/2/17 14:47
 */
public interface Iterator<T> {

    boolean hasNext();

    T next();

}
