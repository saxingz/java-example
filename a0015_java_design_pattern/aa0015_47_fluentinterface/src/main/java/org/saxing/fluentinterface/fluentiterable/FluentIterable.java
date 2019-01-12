package org.saxing.fluentinterface.fluentiterable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * The FluentIterable is a more convenient implementation of the common iterable interface based on
 * the fluent interface design pattern. This interface defines common operations, but doesn't aim to
 * be complete. It was inspired by Guava's com.google.common.collect.FluentIterable.
 * 
 * @param <E>
 *     
 * @author saxing 2019/1/12 9:26
 */
public interface FluentIterable<E> extends Iterable<E> {

    FluentIterable<E> filter(Predicate<? super E> predicate);

    Optional<E> first();

    FluentIterable<E> first(int count);

    Optional<E> last();

    FluentIterable<E> last(int count);

    <T> FluentIterable<T> map(Function<? super E, T> function);

    List<E> asList();

    static <E> List<E> copyToList(Iterable<E> iterable){
        List<E> copy = new ArrayList<>();
        Iterator<E> iterator = iterable.iterator();
        while (iterator.hasNext()){
            copy.add(iterator.next());
        }
        return copy;
    }
}
