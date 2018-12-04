package org.saxing.databus.data;

import org.saxing.databus.AbstractDataType;
import org.saxing.databus.DataType;

import java.time.LocalDateTime;

/**
 * An event raised when applications stops, containing the stop time of the application.
 *
 * @author saxing 2018/12/4 10:34
 */
public class StoppingData extends AbstractDataType {

    private final LocalDateTime when;

    public StoppingData(LocalDateTime when) {
        this.when = when;
    }

    public LocalDateTime getWhen() {
        return when;
    }

    public static DataType of(final LocalDateTime when) {
        return new StoppingData(when);
    }
}
