package org.saxing.event_sourcing.processor;

import org.saxing.event_sourcing.event.DomainEvent;

/**
 * this is the implementation of event processor.
 * All events are processed by this class.
 * This processor uses processorJournal to persist and recover events.
 * 
 * @author saxing 2019/1/3 23:40
 */
public class DomainEventProcessor {

    private final JsonFileJournal processorJournal = new JsonFileJournal();

    public void process(DomainEvent domainEvent){
        domainEvent.process();
        processorJournal.write(domainEvent);
    }

    public void reset(){
        processorJournal.reset();
    }

    public void recover(){
        DomainEvent domainEvent;
        while (true){
            domainEvent = processorJournal.readNext();
            if (domainEvent == null){
                break;
            } else {
                domainEvent.process();
            }
        }
    }
}
