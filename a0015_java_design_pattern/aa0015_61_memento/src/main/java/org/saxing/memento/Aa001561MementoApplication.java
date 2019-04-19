package org.saxing.memento;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Stack;

/**
 * main
 *
 * @author saxing 2019/4/19 16:50
 */
public class Aa001561MementoApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(Aa001561MementoApplication.class);

    public static void main(String[] args) {
        
        Stack<StarMemento> states = new Stack<>();

        Star star = new Star(StarType.SUN, 10000000, 500000);
        LOGGER.info(star.toString());
        states.add(star.getMemento());
        star.timePasses();
        LOGGER.info(star.toString());
        states.add(star.getMemento());
        star.timePasses();
        LOGGER.info(star.toString());
        states.add(star.getMemento());
        star.timePasses();
        LOGGER.info(star.toString());
        states.add(star.getMemento());
        star.timePasses();
        LOGGER.info(star.toString());
        while (states.size() > 0) {
            star.setMemento(states.pop());
            LOGGER.info(star.toString());
        }

    }

}
