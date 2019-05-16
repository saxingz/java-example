package org.saxing.observer.generic;

import org.saxing.observer.WeatherType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * GWeather
 *
 * @author saxing 2019/5/16 15:02
 */
public class GWeather extends Observable<GWeather, Race, WeatherType>   {

    private static final Logger LOGGER = LoggerFactory.getLogger(GWeather.class);

    private WeatherType currentWeather;

    public GWeather() {
        currentWeather = WeatherType.SUNNY;
    }

    /**
     * Makes time pass for weather
     */
    public void timePasses() {
        WeatherType[] enumValues = WeatherType.values();
        currentWeather = enumValues[(currentWeather.ordinal() + 1) % enumValues.length];
        LOGGER.info("The weather changed to {}.", currentWeather);
        notifyObservers(currentWeather);
    }
}
