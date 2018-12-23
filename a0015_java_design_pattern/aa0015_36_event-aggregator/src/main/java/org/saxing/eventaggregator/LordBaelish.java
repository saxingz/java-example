package org.saxing.eventaggregator;

/**
 * LordBaelish produces events.
 *
 * @author saxing 2018/12/23 17:40
 */
public class LordBaelish extends EventEmitter {

    public LordBaelish() {
    }

    public LordBaelish(EventObserver obs) {
        super(obs);
    }

    @Override
    public void timePasses(Weekday day) {
        if (day.equals(Weekday.FRIDAY)) {
            notifyObservers(Event.STARK_SIGHTED);
        }
    }
}
