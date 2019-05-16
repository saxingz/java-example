package org.saxing.observer.generic;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Generic observer inspired by Java Generics and Collection by {@literal Naftalin & Wadler}
 *
 * @param <S>
 * @param <O>
 * @param <A>
 *
 *     @author saxing 2019/5/16 14:40
 */
public abstract class Observable<S extends Observable<S, O, A>, O extends Observer<S, O, A>, A> {

    protected List<O> observers;

    public Observable() {
        this.observers = new CopyOnWriteArrayList<>();
    }

    public void addObserver(O observer) {
        this.observers.add(observer);
    }

    public void removeObserver(O observer) {
        this.observers.remove(observer);
    }

    /**
     * Notify observers
     */
    @SuppressWarnings("unchecked")
    public void notifyObservers(A argument) {
        for (O observer : observers) {
            observer.update((S) this, argument);
        }
    }

}
