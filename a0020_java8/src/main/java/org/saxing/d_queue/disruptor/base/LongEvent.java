package org.saxing.d_queue.disruptor.base;

/**
 * long event
 *
 * http://ifeve.com/disruptor-getting-started/
 *
 * @author saxing 2020/5/2 10:10
 */
public class LongEvent {

    private long value;
    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

}
