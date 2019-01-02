package org.saxing.event_sourcing.event;

import java.io.Serializable;

/**
 * This is the base class for domain events. All events must extend this class.
 *
 * @author saxing 2019/1/2 23:35
 */
public abstract class DomainEvent implements Serializable {

    private final long sequenceId;
    private final long createdTime;
    private final String eventClassName;
    private boolean realTime = true;

    public DomainEvent(long sequenceId, long createdTime, String eventClassName) {
        this.sequenceId = sequenceId;
        this.createdTime = createdTime;
        this.eventClassName = eventClassName;
    }

    /**
     * Process.
     */
    public abstract void process();

    public long getSequenceId() {
        return sequenceId;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public String getEventClassName() {
        return eventClassName;
    }

    public boolean isRealTime() {
        return realTime;
    }

    public void setRealTime(boolean realTime) {
        this.realTime = realTime;
    }
}
