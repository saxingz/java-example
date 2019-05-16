package org.saxing.observer;

import org.saxing.observer.generic.GHobbits;
import org.saxing.observer.generic.GOrcs;
import org.saxing.observer.generic.GWeather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * main
 *
 * @author saxing 2019/5/16 15:07
 */
public class Aa001575ObserverApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(Aa001575ObserverApplication.class);

    public static void main(String[] args) {
        Weather weather = new Weather();
        weather.addObserver(new Orcs());
        weather.addObserver(new Hobbits());

        weather.timePasses();
        weather.timePasses();
        weather.timePasses();
        weather.timePasses();

        // Generic observer inspired by Java Generics and Collection by Naftalin & Wadler
        LOGGER.info("--Running generic version--");
        GWeather gWeather = new GWeather();
        gWeather.addObserver(new GOrcs());
        gWeather.addObserver(new GHobbits());

        gWeather.timePasses();
        gWeather.timePasses();
        gWeather.timePasses();
        gWeather.timePasses();
    }

}
