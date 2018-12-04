package org.saxing.databus.members;

import org.saxing.databus.DataType;
import org.saxing.databus.Member;
import org.saxing.databus.data.MessageData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * Receiver of Data-Bus events that collects the messages from each {@link MessageData}.
 * 
 * @author saxing 2018/12/4 10:51
 */
public class MessageCollectorMember implements Member {

    private static final Logger LOGGER = Logger.getLogger(MessageCollectorMember.class.getName());

    private final String name;

    private List<String> message = new ArrayList<>();

    public MessageCollectorMember(String name) {
        this.name = name;
    }

    @Override
    public void accept(DataType data) {
        if (data instanceof MessageData){
            handleEvent((MessageData) data);
        }
    }

    private void handleEvent(MessageData data){
        LOGGER.info(String.format("%s sees message %s", name, data.getMessage()));
        message.add(data.getMessage());
    }

    public List<String> getMessage() {
        return Collections.unmodifiableList(message);
    }
}
