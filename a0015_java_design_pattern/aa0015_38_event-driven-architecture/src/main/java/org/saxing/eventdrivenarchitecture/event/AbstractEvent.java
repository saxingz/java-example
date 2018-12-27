package org.saxing.eventdrivenarchitecture.event;


import org.saxing.eventdrivenarchitecture.framework.Event;
import org.saxing.eventdrivenarchitecture.framework.EventDispatcher;

/**
 * abstract event
 *
 * @author saxing 2018/12/27 22:28
 */
public abstract class AbstractEvent implements Event {

    /**
     * Returns the event type as a {@link Class} object
     * In this example, this method is used by the {@link EventDispatcher} to
     * dispatch events depending on their type.
     *
     * @return the AbstractEvent type as a {@link Class}.
     */
    public Class<? extends Event> getType(){
        return getClass();
    }

}
