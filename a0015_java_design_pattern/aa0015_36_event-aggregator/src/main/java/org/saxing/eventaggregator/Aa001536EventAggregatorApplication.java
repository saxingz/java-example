package org.saxing.eventaggregator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * main
 *
 * @author saxing 2018/12/23 17:42
 */
public class Aa001536EventAggregatorApplication {

    public static void main(String[] args) {
        KingJoffrey kingJoffrey = new KingJoffrey();
        KingsHand kingsHand = new KingsHand(kingJoffrey);

        List<EventEmitter> eventEmitters = new ArrayList<>();
        eventEmitters.add(kingsHand);
        eventEmitters.add(new LordBaelish(kingsHand));
        eventEmitters.add(new LordVarys(kingsHand));
        eventEmitters.add(new Scout(kingsHand));

        for (Weekday weekday : Weekday.values()){
            for (EventEmitter eventEmitter : eventEmitters){
                eventEmitter.timePasses(weekday);
            }
        }
    }

}

