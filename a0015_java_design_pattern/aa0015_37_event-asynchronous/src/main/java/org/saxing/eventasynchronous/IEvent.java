package org.saxing.eventasynchronous;

/**
 * Events that fulfill the start stop and list out current status behaviour
 *  follow this interface
 *  
 *  @author saxing 2018/12/24 23:09
 */
public interface IEvent {

    void start();

    void stop();

    void status();
    
}
