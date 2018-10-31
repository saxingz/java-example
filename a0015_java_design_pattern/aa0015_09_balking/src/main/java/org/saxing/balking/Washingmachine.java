package org.saxing.balking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * washing machine
 *
 * @author 刘罕  2018/10/31 23:29
 */
public class Washingmachine {

    private static final Logger LOGGER = LoggerFactory.getLogger(Washingmachine.class);

    private WashingmachineState washingmachineState;

    public Washingmachine() {
        washingmachineState = WashingmachineState.ENABLED;
    }

    public WashingmachineState getWashingmachineState() {
        return washingmachineState;
    }

    public void wash(){
        synchronized (this){
            LOGGER.info("{}, Actual machine state: {}", Thread.currentThread().getName(), getWashingmachineState());
            if (washingmachineState == WashingmachineState.WASHING){
                LOGGER.error("ERROR: Cannot wash if the machine has been already washing!");
                return;
            }
            washingmachineState = WashingmachineState.WASHING;
        }
        LOGGER.info("{}, done the wash", Thread.currentThread().getName());
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        endOfWashing();
    }

    public synchronized void endOfWashing(){
        washingmachineState = WashingmachineState.ENABLED;
        LOGGER.info("{}, Washing completed. ", Thread.currentThread().getName());
    }
}
