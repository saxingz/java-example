package org.saxing.fluentinterface.fluentiterable.simple;

import org.saxing.fluentinterface.fluentiterable.FluentIterable;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * This is a simple implementation of the FluentIterable interface. It evaluates all chained
 * operations eagerly. This implementation would be costly to be utilized in real applications.
 *
 * @param <E> the type of the objects the iteration is about
 *
 *           @author saxing 2019/1/12 10:35
 */
public class SimpleFluentIterable<E> implements FluentIterable<E> {

    private final Iterable<E> iterable;

    protected SimpleFluentIterable(Iterable<E> iterable) {
        this.iterable = iterable;
    }

    @Override
    public final FluentIterable<E> filter(Predicate<? super E> predicate) {
        Iterator<E> iterator = iterator();
        while (iterator.hasNext()){
            E nextElement = iterator.next();
            if (!predicate.test(nextElement)){
                iterator.remove();
            }
        }
        return this;
    }

    @Override
    public final Optional<E> first() {
        Iterator<E> resultIterator = first(1).iterator();
        return resultIterator.hasNext() ? Optional.of(resultIterator.next()) : Optional.empty();
    }

    @Override
    public final FluentIterable<E> first(int count) {
        Iterator<E> iterator = iterator();
        int currentCount = 0;
        while (iterator.hasNext()){
            iterator.next();
            if (currentCount >= count){
                iterator.remove();
            }
            currentCount++;
        }
        return this;
    }

    @Override
    public Optional<E> last() {
        List<E> list = last(1).asList();
        if (list.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(list.get(0));
    }

    @Override
    public FluentIterable<E> last(int count) {
        int remainingElementsCount = getRemainingElementsCount();
        Iterator<E> iterator = iterator();
        int currentIndex = 0;
        while (iterator.hasNext()){
            iterator.next();
            if (currentIndex < remainingElementsCount - count){
                iterator.remove();
            }
            currentIndex++;
        }
        return this;
    }

    private final int getRemainingElementsCount() {
        int counter = 0;
        Iterator<E> iterator = iterator();
        while (iterator.hasNext()){
            iterator.next();
            counter++;
        }
        return counter;

    }

    @Override
    public <T> FluentIterable<T> map(Function<? super E, T> function) {
        return null;
    }

    @Override
    public List<E> asList() {
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
