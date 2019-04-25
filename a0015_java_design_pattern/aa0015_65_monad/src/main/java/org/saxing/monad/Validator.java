package org.saxing.monad;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * validator
 *
 * @param <T>
 *
 * @author saxing 2019/4/25 16:53
 */
public class Validator<T> {

    /**
     * Object that is validated
     */
    private final T t;

    /**
     * List of exception thrown during validation.
     */
    private final List<Throwable> exceptions = new ArrayList<>();

    /**
     * Creates a monadic value of given object.
     * @param t object to be validated
     */
    private Validator(T t) {
        this.t = t;
    }

    /**
     * Creates validator against given object
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Validator<T> of(T t){
        return new Validator<>(Objects.requireNonNull(t));
    }

    /**
     * @param validation
     * @param message
     * @return
     */
    public Validator<T> validate(Predicate<T> validation, String message) {
        if (!validation.test(t)) {
            exceptions.add(new IllegalStateException(message));
        }
        return this;
    }

    /**
     * Extension for the {@link Validator#validate(Function, Predicate, String)} method,
     * dedicated for objects, that need to be projected before requested validation.
     *
     * @param projection
     * @param validation
     * @param message
     * @param <U>
     * @return
     */
    public <U> Validator<T> validate(Function<T, U> projection, Predicate<U> validation, String message){
        return validate(projection.andThen(validation::test)::apply, message);
    }

    /**
     * Receives validated object or throws exception when invalid.
     *
     * @return object that was validated
     * @throws IllegalStateException when any validation step results with failure
     */
    public T get() throws IllegalStateException {
        if (exceptions.isEmpty()) {
            return t;
        }
        IllegalStateException e = new IllegalStateException();
        exceptions.forEach(e::addSuppressed);
        throw e;
    }
}
