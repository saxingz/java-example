package org.saxing.eventdrivenarchitecture.framework;

/**
 * This interface can be implemented to handle different types of messages.
 * Every handler is responsible for a single of type message
 * @param <E> Handler can handle events of type E
 *
 * @author saxing 2018/12/27 22:22
 */
public interface Handler<E extends Event> {

    /**
     * The onEvent method should implement and handle behavior related to the event.
     * This can be as simple as calling another service to handle the event on publishing the event on
     * a queue to be consumed by other sub systems.
     * @param event the {@link Event} object to be handled.
     */
    void onEvent(E event);

}
