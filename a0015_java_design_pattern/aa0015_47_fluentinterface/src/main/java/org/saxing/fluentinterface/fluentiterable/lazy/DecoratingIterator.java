package org.saxing.fluentinterface.fluentiterable.lazy;

import java.util.Iterator;

/**
 *
 * This class is used to realize LazyFluentIterables. It decorates a given iterator. Does not
 * support consecutive hasNext() calls.
 * @param <E> Iterable Collection of Elements of Type E
 *
 *           @author saxing 2019/1/12 9:33
 */
public abstract class DecoratingIterator<E> implements Iterator<E> {

    protected final Iterator<E> fromIterator;

    private E next;

    public DecoratingIterator(Iterator<E> fromIterator) {
        this.fromIterator = fromIterator;
    }

    @Override
    public boolean hasNext() {
        next = computeNext();
        return next != null;
    }

    @Override
    public E next() {
        if (next == null){
            return fromIterator.next();
        } else {
            final E result = next;
            next = null;
            return result;
        }
    }

    public abstract E computeNext();
}
