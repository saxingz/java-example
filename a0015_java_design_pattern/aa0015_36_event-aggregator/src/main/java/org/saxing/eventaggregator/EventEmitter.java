package org.saxing.eventaggregator;

import java.util.LinkedList;
import java.util.List;

/**
 * EventEmitter
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

}
