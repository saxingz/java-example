package org.saxing.eventaggregator;

/**
 * Scout produces events.
 *
 * @author saxing 2018/12/23 17:34
 */
public class Scout extends EventEmitter {

    public Scout() {
    }

    public Scout(EventObserver obs) {
        super(obs);
    }

    @Override
    public void timePasses(Weekday day) {
        if (day.equals(Weekday.TUESDAY)) {
            notifyObservers(Event.WARSHIPS_APPROACHING);
        }
    }
}
