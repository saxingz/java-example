package org.saxing.eventaggregator;

/**
 * KingsHand observes events from multiple sources and delivers them to listeners.
 * 
 * @author saxing 2018/12/23 17:38
 */
public class KingsHand extends EventEmitter implements EventObserver {

    public KingsHand() {
    }

    public KingsHand(EventObserver obs) {
        super(obs);
    }

    @Override
    public void timePasses(Weekday day) {
        // NOP
    }

    @Override
    public void onEvent(Event e) {
        notifyObservers(e);
    }
}
