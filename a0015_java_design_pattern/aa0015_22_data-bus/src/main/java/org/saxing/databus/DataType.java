package org.saxing.databus;

import java.util.HashSet;
import java.util.Set;

/**
 * Data type
 *
 * @author saxing 2018/12/3 15:41
 */
public interface DataType {

    /**
     * Returns the data-bus the event is being sent on.
     *
     * @return
     */
    DataBus getDataBus();

    /**
     * Set the data-bus the event will be sent on.
     *
     * @param dataBus
     */
    void setDataBus(DataBus dataBus);

}
