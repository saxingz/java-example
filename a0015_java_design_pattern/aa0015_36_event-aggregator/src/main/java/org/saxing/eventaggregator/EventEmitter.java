package org.saxing.eventaggregator;

import java.util.LinkedList;
import java.util.List;

/**
 * EventEmitter
 *
 * EventEmitter is the base class for event producers that can be observed.
 * 
 * @author saxing 2018/12/23 17:19
 */
public abstract class EventEmitter {

    private List<EventObserver> observers;

    public EventEmitter() {
        this.observers = new LinkedList<>();
    }

    public EventEmitter(EventObserver obs){
        this();
        registerObserver(obs);
    }

    public void registerObserver(EventObserver obs){
        observers.add(obs);
    }

    protected void notifyObservers(Event e){
        for (EventObserver obs : observers){
            obs.onEvent(e);
        }
    }

    public abstract void timePasses(Weekday day);
}
