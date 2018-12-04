package org.saxing.databus;

/**
 * Base for data to send via the Data-Bus.
 * 
 * @author saxing 2018/12/4 9:53
 */
public class AbstractDataType implements DataType {

    private DataBus dataBus;
    
    @Override
    public DataBus getDataBus() {
        return dataBus;
    }

    @Override
    public void setDataBus(DataBus dataBus) {
        this.dataBus = dataBus;
    }
}
