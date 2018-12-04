package org.saxing.databus.data;

import org.saxing.databus.AbstractDataType;
import org.saxing.databus.DataType;

/**
 * An event raised when a string message is sent.
 *
 * @author saxing 2018/12/4 10:27
 */
public class MessageData extends AbstractDataType {

    private final String message;


    public MessageData(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static DataType of(final String message){
        return new MessageData(message);
    }
}
