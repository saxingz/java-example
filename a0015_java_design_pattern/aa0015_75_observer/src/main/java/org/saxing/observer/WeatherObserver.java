package org.saxing.observer;

/**
 * Observer interface.
 *
 * @author saxing 2019/5/16 14:28
 */
public interface WeatherObserver {

    void update(WeatherType currentWeather);

}
