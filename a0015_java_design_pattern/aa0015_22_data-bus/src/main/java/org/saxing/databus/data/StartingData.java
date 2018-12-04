package org.saxing.databus.data;

import org.saxing.databus.AbstractDataType;
import org.saxing.databus.DataType;

import java.time.LocalDateTime;

/**
 * An event raised when applications starts, containing the start time of the application.
 *
 * @author saxing 2018/12/4 10:32
 */
public class StartingData extends AbstractDataType {

    private final LocalDateTime when;

    public StartingData(LocalDateTime when) {
        this.when = when;
    }

    public LocalDateTime getWhen() {
        return when;
    }

    public static DataType of(final LocalDateTime when){
        return new StartingData(when);
    }
}
