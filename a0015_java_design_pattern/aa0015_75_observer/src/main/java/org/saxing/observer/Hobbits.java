package org.saxing.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hobbits
 *
 * @author saxing 2019/5/16 14:30
 */
public class Hobbits implements WeatherObserver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Hobbits.class);

    @Override
    public void update(WeatherType currentWeather) {
        switch (currentWeather) {
            case COLD:
                LOGGER.info("The hobbits are shivering in the cold weather.");
                break;
            case RAINY:
                LOGGER.info("The hobbits look for cover from the rain.");
                break;
            case SUNNY:
                LOGGER.info("The happy hobbits bade in the warm sun.");
                break;
            case WINDY:
                LOGGER.info("The hobbits hold their hats tightly in the windy weather.");
                break;
            default:
                break;
        }
    }
}
