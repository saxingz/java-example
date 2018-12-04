package org.saxing.databus;

import java.util.HashSet;
import java.util.Set;

/**
 * Data bus
 *
 * @author saxing 2018/12/3 15:40
 */
public class DataBus {

    private static final DataBus INSTANCE = new DataBus();

    private final Set<Member> listeners = new HashSet<>();

    public static DataBus getInstance(){
        return INSTANCE;
    }

    /**
     * Register a member with the data-bus to start receiving events.
     *
     * @param member
     */
    public void subscribe(final Member member){
        this.listeners.add(member);
    }

    /**
     * Deregister a member to stop receiving events.
     *
     * @param member
     */
    public void unsubscribe(final Member member){
        this.listeners.remove(member);
    }


    /**
     * Publish and event to all members.
     *
     * @param event
     */
    public void publish(final DataType event){
        event.setDataBus(this);
        listeners.forEach(listener -> listener.accept(event));
    }

}
