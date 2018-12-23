package org.saxing.eventaggregator;

/**
 * LordVarys produces events.
 *
 * @author saxing 2018/12/23 17:41
 */
public class LordVarys extends EventEmitter {

    public LordVarys() {
    }

    public LordVarys(EventObserver obs) {
        super(obs);
    }

    @Override
    public void timePasses(Weekday day) {
        if (day.equals(Weekday.SATURDAY)) {
            notifyObservers(Event.TRAITOR_DETECTED);
        }
    }
}
