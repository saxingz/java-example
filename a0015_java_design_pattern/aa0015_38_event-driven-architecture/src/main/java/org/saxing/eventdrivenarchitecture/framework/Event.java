package org.saxing.eventdrivenarchitecture.framework;

/**
 * Event
 *
 * @author saxing 2018/12/27 22:20
 */
public interface Event {

    /**
     *
     * Returns the message type as a {@link Class} object. In this example the message type is
     *  used to handle events by their type.
     *  @return the message type as a {@link Class}.
     */
    Class<? extends Event> getType();

}
