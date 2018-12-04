package org.saxing.databus.members;

import org.saxing.databus.DataType;
import org.saxing.databus.Member;
import org.saxing.databus.data.MessageData;
import org.saxing.databus.data.StartingData;
import org.saxing.databus.data.StoppingData;

import java.time.LocalDateTime;
import java.util.logging.Logger;

/**
 * private static final Logger LOGGER = Logger.getLogger(StatusMember.class.getName());
 * 
 * @author saxing 2018/12/4 10:41
 */
public class StatusMember implements Member {

    private static final Logger LOGGER = Logger.getLogger(StatusMember.class.getName());

    private final int id;

    private LocalDateTime started;

    private LocalDateTime stopped;

    public StatusMember(int id) {
        this.id = id;
    }

    @Override
    public void accept(DataType data) {
        if (data instanceof StartingData){
            handleEvent((StartingData) data);
        } else if (data instanceof StoppingData){
            handleEvent((StoppingData) data);
        }
    }

    private void handleEvent(StartingData data){
        started = data.getWhen();
        LOGGER.info(String.format("Receiver #%d sees application started at %s", id, started));
    }

    private void handleEvent(StoppingData data){
        stopped = data.getWhen();
        LOGGER.info(String.format("Receiver #%d sees application stopping at %s", id, stopped));
        LOGGER.info(String.format("Receiver #%d sending goodbye message", id));
        data.getDataBus().publish(MessageData.of(String.format("Goodbye cruel world from #%d!", id)));
    }

    public LocalDateTime getStarted() {
        return started;
    }

    public LocalDateTime getStopped() {
        return stopped;
    }
}
