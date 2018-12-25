package org.saxing.eventasynchronous;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * EventManager handles and maintains a pool of event threads. {@link Event} threads are created upon user request. Thre
 *  * are two types of events; Asynchronous and Synchronous. There can be multiple Asynchronous events running at once but
 *  * only one Synchronous event running at a time. Currently supported event operations are: start, stop, and getStatus.
 *  * Once an event is complete, it then notifies EventManager through a listener. The EventManager then takes the event
 *  * out of the pool.
 *
 * @author saxing 2018/12/25 21:10
 */
public class EventManager implements ThreadCompleteListener {

    public static final int MAX_RUNNING_EVENTS = 1000; // Just don't wanna have too many running events. :)
    public static final int MIN_ID = 1;
    public static final int MAX_ID = MAX_RUNNING_EVENTS;
    public static final int MAX_EVENT_TIME = 1800; // in seconds / 30 minutes.
    private int currentlyRunningSyncEvent = -1;
    private Random rand;
    private Map<Integer, Event> eventPool;

    public EventManager() {
        rand = new Random(1);
        eventPool = new ConcurrentHashMap<Integer, Event>(MAX_RUNNING_EVENTS);
    }

    /**
     * Create a Synchronous event.
     *
     * @param eventTime
     * @return
     */
    public int create(int eventTime)
            throws MaxNumOfEventsAllowedException, InvalidOperationException, LongRunningEventException {
        if (currentlyRunningSyncEvent != -1){
            throw new InvalidOperationException(
                    "Event [" + currentlyRunningSyncEvent + "] is still running. Please wait until it finishes and try again.");
        }
        int eventId = createEvent(eventTime, true);
        currentlyRunningSyncEvent = eventId;

        return eventId;
    }

    /**
     * create event
     *
     * @param eventTime
     * @param isSynchronous
     * @return
     */
    private int createEvent(int eventTime, boolean isSynchronous)
        throws MaxNumOfEventsAllowedException, LongRunningEventException{
        if (eventPool.size() == MAX_RUNNING_EVENTS){
            throw new MaxNumOfEventsAllowedException("Too many events are running at the moment. Please try again later.");
        }

        if (eventTime >= MAX_RUNNING_EVENTS){
            throw new LongRunningEventException(
                    "Maximum event time allowed is " + MAX_EVENT_TIME + " seconds. Please try again.");
        }

        int newEventId = generateId();

        Event newEvent = new Event(newEventId, eventTime, isSynchronous);
        newEvent.addListener(this);
        eventPool.put(newEventId, newEvent);

        return newEventId;
    }

    /**
     * generateId
     *
     * @return
     */
    private int generateId(){
        int randomNum = rand.nextInt((MAX_ID - MIN_ID) + 1) + MIN_ID;
        while (eventPool.containsKey(randomNum)){
            randomNum = rand.nextInt((MAX_ID - MIN_ID) + 1) + MIN_ID;
        }
        return randomNum;
    }

    @Override
    public void completedEventHandler(int eventId) {

    }
}
