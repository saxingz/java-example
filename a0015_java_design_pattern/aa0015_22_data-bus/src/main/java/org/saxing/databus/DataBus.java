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
        listeners.add(member);
    }

    public void unsubscribe(final Member member){

    }

}
