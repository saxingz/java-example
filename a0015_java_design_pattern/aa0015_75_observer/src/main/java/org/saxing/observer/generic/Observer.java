package org.saxing.observer.generic;

/**
 *
 * Observer
 *
 * @param <S> Observable
 * @param <O> Observer
 * @param <A> Action
 *
 *           @author saxing 2019/5/16 14:37
 */
public interface Observer<S extends Observable<S, O, A>, O extends Observer<S, O, A>, A> {

    void update(S subject, A argument);

}
