package org.saxing.fluentinterface.fluentiterable.lazy;

import org.saxing.fluentinterface.fluentiterable.FluentIterable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * This is a lazy implementation of the FluentIterable interface. It evaluates all chained
 * operations when a terminating operation is applied.
 * @param <E>
 *
 *     @author saxing 2019/1/12 9:39
 */
public class LazyFluentIterable<E> implements FluentIterable<E> {

    private final Iterable<E> iterable;

    /**
     * This constructor creates a new LazyFluentIterable. It wraps the given iterable.
     *
     * @param iterable
     */
    protected LazyFluentIterable(Iterable<E> iterable) {
        this.iterable = iterable;
    }

    /**
     * This constructor can be used to implement anonymous subclasses of the LazyFluentIterable.
     */
    protected LazyFluentIterable(){
        iterable = this;
    }

    @Override
    public FluentIterable<E> filter(Predicate<? super E> predicate) {
        return new LazyFluentIterable<E>(){
            @Override
            public Iterator<E> iterator() {
                return new DecoratingIterator<E>(iterable.iterator()) {
                    @Override
                    public E computeNext() {
                        while (fromIterator.hasNext()){
                            E candicate = fromIterator.next();
                            if (predicate.test(candicate)){
                                return candicate;
                            }
                        }
                        return null;
                    }
                };
            }
        };
    }

    @Override
    public Optional<E> first() {
        Iterator<E> resultIterator = first(1).iterator();
        return resultIterator.hasNext() ? Optional.of(resultIterator.next()) : Optional.empty();
    }

    @Override
    public FluentIterable<E> first(int count) {
        return new LazyFluentIterable<E>(){
            @Override
            public Iterator<E> iterator() {
                return new DecoratingIterator<E>(iterable.iterator()) {
                    int currentIndex;

                    @Override
                    public E computeNext() {
                        if (currentIndex < count && fromIterator.hasNext()){
                            E candicate = fromIterator.next();
                            currentIndex++;
                            return candicate;
                        }
                        return null;
                    }
                };
            }
        };
    }

    @Override
    public Optional<E> last() {
        Iterator<E> resultIterator = last(1).iterator();
        return resultIterator.hasNext() ? Optional.of(resultIterator.next()) : Optional.empty();
    }

    @Override
    public FluentIterable<E> last(int count) {
        return new LazyFluentIterable<E>(){
            @Override
            public Iterator<E> iterator() {
                return new DecoratingIterator<E>(iterable.iterator()) {

                    private int stopIndex;
                    private int totalElementsCount;
                    private List<E> list;
                    private int currentIndex;

                    @Override
                    public E computeNext() {
                        initialize();

                        E candicate = null;
                        while (currentIndex < stopIndex && fromIterator.hasNext()){
                            currentIndex++;
                            fromIterator.next();
                        }
                        if (currentIndex >= stopIndex && fromIterator.hasNext() ){
                            candicate = fromIterator.next();
                        }
                        return candicate;
                    }

                    private void initialize(){
                        if (list == null){
                            list = new ArrayList<>();
                            Iterator<E> newIterator = iterable.iterator();
                            while (newIterator.hasNext()){
                                list.add(newIterator.next());
                            }
                            totalElementsCount = list.size();
                            stopIndex = totalElementsCount - count;
                        }
                    }
                };
            }
        };
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
