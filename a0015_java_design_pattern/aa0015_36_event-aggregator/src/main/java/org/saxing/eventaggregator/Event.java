package org.saxing.eventaggregator;

/**
 * Event
 * 
 * @author saxing 2018/12/23 17:16
 */
public enum Event {

    STARK_SIGHTED("Stark sighted"),
    WARSHIPS_APPROACHING("Warships approaching"),
    TRAITOR_DETECTED("Traitor detected"),
    ;

    private String description;

    Event(String description) {
        this.description = description;
    }

    public String toString() {
        return description;
    }

}
